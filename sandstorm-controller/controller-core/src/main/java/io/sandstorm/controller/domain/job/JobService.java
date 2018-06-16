package io.sandstorm.controller.domain.job;

import io.sandstorm.common.BizAssert;
import io.sandstorm.common.CaseCode;
import io.sandstorm.controller.domain.job.util.Maths;
import io.sandstorm.controller.domain.resource.DataChunk;
import io.sandstorm.controller.domain.resource.DataChunkRepo;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.LoadInjector;
import io.sandstorm.controller.domain.resource.LoadInjectorRepo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {

    private static final Object lock_for_injectors = new Object();

    @Resource
    private LoadInjectorRepo loadInjectorRepo;
    @Resource
    private DataChunkRepo dataChunkRepo;
    @Resource
    private TestJobRepo testJobRepo;
    @Resource
    private JobExecRepo jobExecRepo;
    @Resource
    private JobSliceExecRepo jobSliceExecRepo;
    @Resource
    private JobOperator jobOperator;

    public JobExecution runJob(ObjectId jobId) {
        TestJob job = testJobRepo.get(jobId);
        RunJobCmd command = new RunJobCmd();
        command.setJobId(jobId);
        command.setExecMode(ExecPlan.ExecMode.periodic);
        command.setLoadProfile(job.loadProfile());
        return runJob(command);
    }

    public JobExecution runJob(RunJobCmd command) {
        TestJob job = testJobRepo.get(command.jobId());
        List<LoadInjector> injectors = acquireInjectors(command.loadProfile().totalInjectors());
        List<DataSet.Slice> dataSlices = assignDataSetToInjectors(
                job.dataSet(),
                command.loadProfile().totalInjectors().intValue());

        JobExecution jobExecution = job.deriveJobExec(command);
        jobExecution.beginDomainLifeCycle();
        List<JobSliceExecution> jobSliceExecs = jobExecution.deriveJobSliceExec(injectors, dataSlices);
        jobSliceExecs.parallelStream().forEach(sliceExec -> sliceExec.beginDomainLifeCycle());

        jobExecRepo.save(jobExecution);
        jobSliceExecRepo.save(jobSliceExecs);
        jobOperator.run(jobExecution, jobSliceExecs);

        return jobExecution;
    }

    private List<LoadInjector> acquireInjectors(long numInjectors) {
        synchronized (lock_for_injectors) {
            List<LoadInjector> usableInjectors = loadInjectorRepo.findUsable(numInjectors);
            BizAssert.check(
                    usableInjectors.size() >= numInjectors,
                    CaseCode.load_injector_not_enough,
                    "Need %s load-injectors, but only %s usable",
                    numInjectors, usableInjectors.size());

            long numUpdated = loadInjectorRepo.markAsInUse(
                    usableInjectors.stream().map(item -> item.id()).collect(Collectors.toList()));
            // The following case should not happen, because only one thread can acquire the lock
            BizAssert.check(
                    numInjectors == numUpdated,
                    CaseCode.load_injector_not_enough,
                    "Need %s load-injectors, but only %s injectors were acquired",
                    numInjectors, numUpdated);
            return usableInjectors;
        }
    }

    private List<DataSet.Slice> assignDataSetToInjectors(Optional<DataSet> dataSet, int numInjectors) {
        if (!dataSet.isPresent()) {
            return Collections.emptyList();
        }

        int numChunksPerInjector = (int) Maths.divideAndCeil(dataSet.get().chunksCount(), numInjectors);
        List<DataSet.Slice> dataSlices = new ArrayList<>(numInjectors);
        for (int i = 0; i < numInjectors; i++) {
            int startNo = i * numChunksPerInjector % dataSet.get().chunksCount() + 1;
            List<DataChunk> chunks = dataChunkRepo.find(dataSet.get().id(), startNo, numChunksPerInjector);
            List<DataChunk.Traits> chunkTraits = chunks.parallelStream()
                    .map(chunk -> chunk.newTraits())
                    .collect(Collectors.toList());
            dataSlices.add(new DataSet.Slice(dataSet.get().feederFileName(), chunkTraits));
        }
        return dataSlices;
    }

}
