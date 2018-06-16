package io.sandstorm.agent.domain;

import io.sandstorm.agent.domain.model.LocalDataChunk;
import io.sandstorm.agent.domain.model.LocalTestJob;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class DataChunkService {

    private final static Logger logger = LoggerFactory.getLogger(DataChunkService.class);

    private static DataChunkService instance = new DataChunkService();
    private final ExecutorService futureExecutor = Executors.newCachedThreadPool(new ThreadFactory() {
        AtomicInteger counter = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "DataChunk-Decompression-Thread-" + counter.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    });

    private DataChunkService() {
    }

    public static DataChunkService getInstance() {
        return instance;
    }

    public String dataChunkDir(LocalTestJob testJob) {
        return testJob.feederFileDir();
    }

    public String dataChunkFilePath(LocalTestJob testJob, String resourceName) {
        StringBuilder dataFilePath = new StringBuilder(128);
        dataFilePath.append(dataChunkDir(testJob))
                .append(File.separator).append(resourceName);
        return dataFilePath.toString();
    }

    public boolean dataChunkNotExist(LocalTestJob testJob, String resourceName, String signature) {
        String dataFilePath = dataChunkFilePath(testJob, resourceName);
        File dataFile = new File(dataFilePath.toString());
        if (!dataFile.exists()) {
            return true;
        }
        try (FileInputStream fileInputStream = new FileInputStream(dataFile)) {
            String md5 = DigestUtils.md5Hex(fileInputStream);
            return !signature.equals(md5);
        } catch (IOException e) {
            //Ignore
        }
        return true;
    }

    public void mergeDataChunks(LocalTestJob testJob) {
        MergeDataChunkTask mergeDataChunkTask = new MergeDataChunkTask(testJob, testJob.feederFileName());
        mergeDataChunkTask.mergeDataChunks();
    }

    private class MergeDataChunkTask {

        private final static String JSON_ARRAY_START_NOTATION = "[";
        private final static String JSON_ARRAY_END_NOTATION = "]";
        private final LocalTestJob testJob;
        private final String feederFileName;

        MergeDataChunkTask(LocalTestJob testJob, String feederFileName) {
            this.testJob = testJob;
            this.feederFileName = feederFileName;
        }

        public void mergeDataChunks() {
            try {
                logger.info("****** Merging test-data files starting ******");
                String feederDataChunkDir = dataChunkDir(testJob);
                String feederFileName = feederDataChunkDir + File.separator + this.feederFileName;
                File targetFile = new File(feederFileName);
                File[] gzippedChunks = new File(feederDataChunkDir).listFiles(file -> file.getName().endsWith(".json.gz"));
                List<File> unzippedChunks = unzipDataChunks(gzippedChunks);
                if (unzippedChunks == null || unzippedChunks.size() == 0) {
                    String message = String.format("No data-chunks to merge");
                    logger.error(message);
                    throw new JobResourceException(message);
                }
                //如果下载成功的数据块数量小于总的需要下载的数据块数量的二分之一，则认为这次下载不成功
                if (unzippedChunks.size() <= testJob.dataChunks().size() * 1.0 / 2) {
                    String message = "Count of downloads of data-chunks is less than (total_count/2)";
                    logger.error(message);
                    throw new JobResourceException(message);
                }
                List<File> orderlyChunkFiles = sortChunkFiles(testJob, unzippedChunks);
                Writer writer = null;
                try {
                    writer = new OutputStreamWriter(new FileOutputStream(targetFile), Charset.forName("utf-8"));
                    mergeChunkFiles(orderlyChunkFiles, writer);
                    IOUtils.closeQuietly(writer);
                    //delete outdated data
                    orderlyChunkFiles.stream().forEach(file -> file.delete());
                } finally {
                    IOUtils.closeQuietly(writer);
                }
            } catch (Exception e) {
                String message = String.format("Failed to merged dataChunks, error : [%s]", e.getMessage());
                logger.error(message, e);
                throw new JobResourceException(message, e);
            } finally {
                logger.info("****** Merging test-data files ended ******");
            }
        }

        private List<File> unzipDataChunks(File[] gzippedChunks) {
            if (null == gzippedChunks || gzippedChunks.length == 0) {
                return Collections.emptyList();
            }
            return Arrays.stream(gzippedChunks)
                    .map(dataChunk -> CompletableFuture.supplyAsync(() -> {
                        String unzippedFilePath = dataChunk.getName().substring(0, dataChunk.getName().lastIndexOf("."));
                        OutputStream outStream = null;
                        GZIPInputStream gzipInStream = null;
                        try {
                            outStream = new FileOutputStream(unzippedFilePath);
                            gzipInStream = new GZIPInputStream(new FileInputStream(dataChunk));
                            IOUtils.copy(gzipInStream, outStream);
                            return new File(unzippedFilePath);
                        } catch (Exception ignore) {
                            //ignore
                            logger.warn(ignore.getMessage(), ignore);
                        } finally {
                            IOUtils.closeQuietly(outStream);
                            IOUtils.closeQuietly(gzipInStream);
                        }
                        return null;
                    }, futureExecutor))
                    .map(CompletableFuture::join)
                    .filter(file -> file != null)
                    .collect(Collectors.toList());
        }

        private List<File> sortChunkFiles(LocalTestJob testJob, List<File> unzippedChunkFiles) {
            List<LocalDataChunk> orderlyChunks = testJob.dataChunks();
            orderlyChunks.sort((thisChunk, thatChunk) -> thisChunk.no() <= thatChunk.no() ? -1 : 1);

            final Map<String, File> nameToFileMap = unzippedChunkFiles.stream()
                    .collect(Collectors.toMap(file -> file.getName(), file -> file));

            return orderlyChunks.stream()
                    .map(dataChunk -> nameToFileMap.get(dataChunk.name().substring(0, dataChunk.name().lastIndexOf("."))))
                    .filter(file -> file != null)
                    .collect(Collectors.toList());
        }

        private void mergeChunkFiles(List<File> orderlyChunkFiles, Writer writer) throws IOException {
            writer.write(JSON_ARRAY_START_NOTATION);
            for (int i = 0; i < orderlyChunkFiles.size(); i++) {
                File chunkFile = orderlyChunkFiles.get(i);
                if (i == orderlyChunkFiles.size() - 1) { // the last chunk-file

                    // Every record in a data-chunk file must be ended with the char ',',
                    // no matter whether the chunk files are produced by hadoop or hand-written
                    RandomAccessFile raFile = new RandomAccessFile(chunkFile, "rw");

                    // move the pointer to the index of the last char ','
                    // at which the next read or write occurs.
                    long pos = raFile.length() - 1;
                    raFile.seek(pos);
                    while (raFile.read() != ',') {
                        pos = pos - 1;
                        raFile.seek(pos);
                    }
                    // Don't forget to move the pointer back by 1 slot, because after you read the last char ',',
                    // the pointer is at the next index.
                    // Attention! the pos is just at the index of the last char ',', rather than (pos - 1).
                    raFile.seek(pos);

                    raFile.write(JSON_ARRAY_END_NOTATION.getBytes("UTF-8"));
                    raFile.seek(0);

                    try (RandomAccessFile inputFile = raFile;
                         InputStream chunkInput = Channels.newInputStream(inputFile.getChannel())) {
                        IOUtils.copy(chunkInput, writer, "UTF-8");
                    }
                } else {
                    try (InputStream chunkInput = new FileInputStream(chunkFile)) {
                        IOUtils.copy(chunkInput, writer, "UTF-8");
                    }
                }
            }
            writer.flush();
        }
    }

}
