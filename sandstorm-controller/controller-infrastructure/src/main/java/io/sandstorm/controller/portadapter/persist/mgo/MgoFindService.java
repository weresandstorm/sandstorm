package io.sandstorm.controller.portadapter.persist.mgo;

import com.querydsl.core.BooleanBuilder;
import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.SimplePagingQParams;
import io.sandstorm.common.query.domain.Sort;
import io.sandstorm.controller.app.DataChunkQParams;
import io.sandstorm.controller.app.FindService;
import io.sandstorm.controller.app.JobExecQParams;
import io.sandstorm.controller.app.JobSliceExecQParams;
import io.sandstorm.controller.app.LoadInjectorQParams;
import io.sandstorm.controller.domain.job.JobExecRepo;
import io.sandstorm.controller.domain.job.JobExecution;
import io.sandstorm.controller.domain.job.JobSliceExecRepo;
import io.sandstorm.controller.domain.job.JobSliceExecution;
import io.sandstorm.controller.domain.job.QJobExecution;
import io.sandstorm.controller.domain.job.QJobSliceExecution;
import io.sandstorm.controller.domain.job.QTestJob;
import io.sandstorm.controller.domain.job.TestJob;
import io.sandstorm.controller.domain.job.TestJobRepo;
import io.sandstorm.controller.domain.resource.DataChunk;
import io.sandstorm.controller.domain.resource.DataChunkRepo;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.DataSetRepo;
import io.sandstorm.controller.domain.resource.LoadInjector;
import io.sandstorm.controller.domain.resource.LoadInjectorRepo;
import io.sandstorm.controller.domain.resource.QDataChunk;
import io.sandstorm.controller.domain.resource.QDataSet;
import io.sandstorm.controller.domain.resource.QLoadInjector;
import io.sandstorm.controller.domain.resource.QTestScript;
import io.sandstorm.controller.domain.resource.TestScript;
import io.sandstorm.controller.domain.resource.TestScriptRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class MgoFindService implements FindService {

    @Resource
    private TestScriptRepo testScriptRepo;
    @Resource
    private DataSetRepo dataSetRepo;
    @Resource
    private DataChunkRepo dataChunkRepo;
    @Resource
    private LoadInjectorRepo loadInjectorRepo;
    @Resource
    private TestJobRepo testJobRepo;
    @Resource
    private JobExecRepo jobExecRepo;
    @Resource
    private JobSliceExecRepo jobSliceExecRepo;

    @Override
    public Page<TestScript> findTestScripts(SimplePagingQParams params) {
        params.setSort(new Sort(Sort.Direction.DESC, "lastUpdated"));
        return testScriptRepo.findPage(
                params.asPageable(),
                () -> params.valToMatch().map(
                        val -> QTestScript.testScript.jarAlias.containsIgnoreCase(val)
                                .or(QTestScript.testScript.desc.containsIgnoreCase(val))
                )
        );
    }

    @Override
    public Page<DataSet> findDataSets(SimplePagingQParams params) {
        params.setSort(new Sort(Sort.Direction.DESC, "lastUpdated"));
        return dataSetRepo.findPage(
                params.asPageable(),
                () -> params.valToMatch().map(
                        val -> QDataSet.dataSet.feederFileName.containsIgnoreCase(val)
                                .or(QDataSet.dataSet.desc.containsIgnoreCase(val))
                )
        );
    }

    @Override
    public Page<DataChunk> findDataChunks(DataChunkQParams params) {
        params.setSort(new Sort(Sort.Direction.ASC, "id"));
        return dataChunkRepo.findPage(
                params.asPageable(),
                () -> Optional.of(QDataChunk.dataChunk.dataSetId.eq(params.getDataSetId())));
    }

    @Override
    public Page<LoadInjector> findLoadInjectors(LoadInjectorQParams params) {
        params.setSort(new Sort(Sort.Direction.DESC, "id"));
        return loadInjectorRepo.findPage(
                params.asPageable(),
                () -> {
                    BooleanBuilder criteriaBuilder = new BooleanBuilder();
                    if (params.state().isPresent()) {
                        criteriaBuilder.and(QLoadInjector.loadInjector.state.eq(params.state().get()));
                    }
                    if (params.ip().isPresent()) {
                        criteriaBuilder.and(
                                QLoadInjector.loadInjector.intranetIp.eq(params.ip().get())
                                        .or(QLoadInjector.loadInjector.publicIp.eq(params.ip().get())));
                    }
                    return Optional.of(criteriaBuilder);
                });
    }

    @Override
    public Page<TestJob> findTestJobs(SimplePagingQParams params) {
        params.setSort(new Sort(Sort.Direction.DESC, "lastUpdated"));
        return testJobRepo.findPage(
                params.asPageable(),
                () -> params.valToMatch().map(
                        val -> QTestJob.testJob.name.containsIgnoreCase(val)
                                .or(QTestJob.testJob.remark.containsIgnoreCase(val))));
    }

    @Override
    public Page<JobExecution> findJobExecutions(JobExecQParams params) {
        params.setSort(new Sort(Sort.Direction.DESC, "lastUpdated"));
        return jobExecRepo.findPage(
                params.asPageable(),
                () -> {
                    BooleanBuilder criteriaBuilder = new BooleanBuilder();
                    criteriaBuilder.and(QJobExecution.jobExecution.jobId.eq(params.getJobId()));
                    if (params.getStatus().isPresent()) {
                        criteriaBuilder.and(QJobExecution.jobExecution.status.eq(params.getStatus().get()));
                    }
                    return Optional.of(criteriaBuilder);
                });
    }

    @Override
    public Page<JobSliceExecution> findJobSliceExecs(JobSliceExecQParams params) {
        params.setSort(new Sort(Sort.Direction.DESC, "lastUpdated"));
        return jobSliceExecRepo.findPage(
                params.asPageable(),
                () -> {
                    BooleanBuilder criteriaBuilder = new BooleanBuilder();
                    criteriaBuilder.and(QJobSliceExecution.jobSliceExecution.jobExecId.eq(params.getJobExecId()));
                    if (params.getStatus().isPresent()) {
                        criteriaBuilder.and(QJobSliceExecution.jobSliceExecution.status.eq(params.getStatus().get()));
                    }
                    return Optional.of(criteriaBuilder);
                });
    }

}