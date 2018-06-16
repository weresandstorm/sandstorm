package io.sandstorm.agent.domain;

import io.sandstorm.agent.domain.model.LocalTestJob;
import io.sandstorm.agent.domain.model.LocalTestScript;
import io.sandstorm.agent.portadapter.gateway.out.ControllerClient;
import io.sandstorm.spi.SpiServiceFinder;
import io.sandstorm.spi.storage.DataSetStorage;
import io.sandstorm.spi.storage.ScriptStorage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.sandstorm.exchange.helper.RpcException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class JobResourceService {

    private static final Logger logger = LoggerFactory.getLogger(JobResourceService.class);

    private static JobResourceService instance = new JobResourceService();
    private final ExecutorService downloadExecutor = Executors.newSingleThreadExecutor(
            (runnable) -> new Thread(runnable, "Test-Resources-Download-Thread"));
    private final ExecutorService futureExecutor = Executors.newCachedThreadPool(new ThreadFactory() {
        AtomicInteger counter = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "DataChunk-Download-Thread-" + counter.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    });
    private ScriptStorage scriptStorage = SpiServiceFinder.findService(ScriptStorage.class);
    private DataSetStorage dataSetStorage = SpiServiceFinder.findService(DataSetStorage.class);
    private DataChunkService dataChunkService = DataChunkService.getInstance();
    private ControllerClient ctrlClient = ControllerClient.getInstance();
    private FutureTask<LocalTestJob> downloadFuture;
    private LocalTestJob currentTestJob;

    private JobResourceService() {
    }

    public static JobResourceService getInstance() {
        return instance;
    }

    public void initDownloader(LocalTestJob testJob) {
        if (null != currentTestJob && currentTestJob.equals(testJob)) {
            return;
        }
        if (downloadFuture != null) {
            downloadFuture.cancel(true);
        }
        downloadFuture = null;
        currentTestJob = testJob;
    }

    public void startDownloadResourcesFor(LocalTestJob testJob) {
        downloadFuture = new FutureTask<>(() -> {
            doStartDownload(currentTestJob);
            return currentTestJob;
        });
        downloadExecutor.submit(downloadFuture);
    }

    private void doStartDownload(final LocalTestJob testJob) {
        logger.info("****** Started asynchronous download test resources ******");
        try {
            downloadTestScript(testJob);
            if (testJob.dataChunks() != null && !testJob.dataChunks().isEmpty()) {
                downloadDataChunks(testJob);
                dataChunkService.mergeDataChunks(testJob);
            }
            ctrlClient.onPreparationSuccessed(testJob.id());
        } catch (RpcException e) {
            String message = String.format(
                    "Succeeded in downloading resources for test-job [id:%s], but failed to notify the controller",
                    testJob);
            logger.error(message, e);
        } catch (Exception e) {
            String message = String.format("Failed to download resource for testJob [%s], error : [%s]", testJob, e.getMessage());
            ctrlClient.onPreparationFailed(testJob.id(), message);
        }
    }

    private void downloadTestScript(LocalTestJob testJob) throws IOException {
        LocalTestScript testScript = testJob.script();
        scriptStorage.get(testScript.jarUrl(), (scriptStream) -> writeScriptToFile(testJob, scriptStream, testScript.jarAlias()));
    }

    private void downloadDataChunks(LocalTestJob testJob) {
        testJob.dataChunks().parallelStream()
                .filter(dataChunk -> dataChunkService.dataChunkNotExist(testJob, dataChunk.name(), dataChunk.signature()))
                .map(dataChunk -> CompletableFuture.supplyAsync(
                        () -> dataSetStorage.get(dataChunk.url(), (chunkStream) -> writeChunkToFile(testJob, chunkStream, dataChunk.name())),
                        futureExecutor))
                .map(future -> future.exceptionally(ex -> {
                    logger.error("Failed to download dataChunk for testJob [{}], error : [{}]",
                            new Object[]{testJob, ex.getMessage()});
                    return null;
                }))
                .map(CompletableFuture::join)
                .collect(Collectors.counting());
    }

    private void writeScriptToFile(LocalTestJob testJob, InputStream resourceInStream, String scriptName) {
        StringBuilder targetFilePath = new StringBuilder(128);
        targetFilePath.append(AgentConfig.testScriptRootDir());
        targetFilePath.append(File.separator).append(scriptName);
        OutputStream outStream = null;
        try {
            FileUtils.cleanDirectory(new File(AgentConfig.testScriptRootDir()));
        } catch (Exception e) {
            logger.warn("Warning: Failed to clean script directory for testJob [{}], error : [{}]", testJob, e.getMessage());
        }
        try {
            File scriptRootDir = new File(AgentConfig.testScriptRootDir());
            if(!scriptRootDir.exists()){
                scriptRootDir.mkdirs();//create directory
            }
            outStream = new FileOutputStream(targetFilePath.toString());
            IOUtils.copy(resourceInStream, outStream);
        } catch (Exception e) {
            throw new JobResourceException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(outStream);
            IOUtils.closeQuietly(resourceInStream);
        }
    }

    private void writeChunkToFile(LocalTestJob testJob, InputStream resourceInStream, String chunkFileName) {
        String targetFilePath = dataChunkService.dataChunkFilePath(testJob, chunkFileName);
        OutputStream outStream = null;
        try {
            File targetFile = new File(targetFilePath);
            if (!targetFile.exists()) {
                FileUtils.touch(targetFile);
            }
            outStream = new FileOutputStream(targetFile);
            IOUtils.copy(resourceInStream, outStream);
        } catch (IOException e) {
            String errMsg = String.format("Download [%s] file failed, and skip this file, error [%s]:", chunkFileName, e.getMessage());
            logger.error(errMsg, e);
        } finally {
            IOUtils.closeQuietly(outStream);
        }
    }
}
