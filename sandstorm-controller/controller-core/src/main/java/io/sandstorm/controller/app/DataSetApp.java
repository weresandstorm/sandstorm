package io.sandstorm.controller.app;

import io.sandstorm.common.BizAssert;
import io.sandstorm.common.CaseCode;
import io.sandstorm.common.IllegalInputException;
import io.sandstorm.common.query.domain.Existence;
import io.sandstorm.controller.domain.job.JobExecRepo;
import io.sandstorm.controller.domain.job.TestJobRepo;
import io.sandstorm.controller.domain.resource.DataChunk;
import io.sandstorm.controller.domain.resource.DataChunkRepo;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.DataSet.SourceType;
import io.sandstorm.controller.domain.resource.DataSetRepo;
import io.sandstorm.controller.domain.resource.DataSetService;
import io.sandstorm.spi.SpiServiceFinder;
import io.sandstorm.spi.storage.DataSetStorage;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataSetApp {

    private DataSetStorage dataSetStorage = SpiServiceFinder.findService(DataSetStorage.class);

    @Resource
    private DataSetRepo dataSetRepo;
    @Resource
    private DataChunkRepo dataChunkRepo;
    @Resource
    private TestJobRepo testJobRepo;
    @Resource
    private JobExecRepo jobExecRepo;
    @Resource
    private LocalTempStorage localTempStorage;
    @Resource
    private DataSetService dataSetService;

    public Existence checkExistence(String feederFileName) {
        return new Existence(dataSetRepo.exist(feederFileName));
    }

    public void createDataSet(DataSetCmd.Create createCmd) {
        BizAssert.check(
                !checkExistence(createCmd.feederFileName()).exist(),
                CaseCode.dataset_file_name_exist,
                "%s with feederFileName %s already exists",
                DataSet.class.getSimpleName(), createCmd.feederFileName()
        );

        List<DataSetStorage.Chunk> chunks = storeDataChunks(createCmd, DataSet.initial_revision);
        DataSet dataSet = new DataSet(
                createCmd.feederFileName(),
                createCmd.desc(),
                createCmd.sourceType(),
                chunks.size(),
                createCmd.sourceUrl(),
                createCmd.operator());
        dataSet.beginDomainLifeCycle();
        List<DataChunk> dataChunks = chunks.stream()
                .map(aChunk -> new DataChunk(
                        aChunk.no(),
                        aChunk.url(),
                        aChunk.signature(),
                        dataSet.id(),
                        dataSet.creator()))
                .collect(Collectors.toList());
        dataSetRepo.save(dataSet);
        dataChunkRepo.save(dataChunks);
    }

    public DataSet detail(ObjectId id) {
        return dataSetRepo.get(id);
    }

    public CheckJobExecsEnded checkReferringJobExecsEnded(ObjectId dataSetId) {
        DataSet dataSet = dataSetRepo.get(dataSetId);
        boolean notEndedExist = jobExecRepo.notEndedExistByDataSet(dataSet);
        return new CheckJobExecsEnded(!notEndedExist);
    }

    public void update(DataSetCmd.Update updateCmd) {
        DataSet theDataSet = dataSetRepo.get(updateCmd.id());
        BizAssert.check(
                theDataSet.feederFileName().equals(updateCmd.feederFileName()),
                "Field feederFileName of %s doesn't allowed to be modified",
                DataSet.class.getSimpleName()
        );
        BizAssert.check(
                checkReferringJobExecsEnded(updateCmd.id()).allEnded(),
                CaseCode.dataset_unchangeable_currently,
                "Not all job-execs referring data-set [id:%s] ended, so the referred data-set is currently unchangeable",
                updateCmd.id()
        );

        int chunksCount = theDataSet.chunksCount();
        if (updateCmd.contentChanged()) {
            int revision = theDataSet.revision() + 1;
            List<DataSetStorage.Chunk> chunks = storeDataChunks(updateCmd, revision);
            chunksCount = chunks.size();
            List<DataChunk> dataChunks = chunks.stream()
                    .map(aChunk -> new DataChunk(
                            aChunk.no(),
                            aChunk.url(),
                            aChunk.signature(),
                            theDataSet.id(),
                            theDataSet.creator()))
                    .collect(Collectors.toList());
            dataSetService.discardDataChunks(theDataSet.id(), updateCmd.operator());
            dataChunkRepo.save(dataChunks);
        }

        DataSet change = new DataSet(
                updateCmd.feederFileName(),
                updateCmd.desc(),
                updateCmd.sourceType(),
                chunksCount,
                updateCmd.sourceUrl(),
                updateCmd.operator());
        theDataSet.mergeFrom(change, updateCmd.contentChanged());
        dataSetRepo.save(theDataSet);
    }

    public void delete(ObjectId dataSetId) {
        BizAssert.check(
                !testJobRepo.existWithDataSet(dataSetId),
                CaseCode.dataset_undeletable,
                "DataSet [id:%s] is still referenced by a TestJob, so can't be deleted",
                dataSetId);
        dataChunkRepo.deleteByDataSet(dataSetId);
        dataSetRepo.delete(dataSetId);
    }

    private List<DataSetStorage.Chunk> storeDataChunks(DataSetCmd.CreateOrUpdate command, int dataSetRevision) {
        List<DataSetStorage.Chunk> chunks;
        if (command.sourceType() == SourceType.url) {
            chunks = dataSetStorage.put(command.feederFileName(), command.sourceUrl().get(), dataSetRevision);
        } else {
            List<InputStream> sourceStreams = command.dataChunks()
                    .stream()
                    .map(tempFile -> localTempStorage.get(tempFile))
                    .collect(Collectors.toList());
            chunks = dataSetStorage.put(command.feederFileName(), sourceStreams, dataSetRevision);
            localTempStorage.remove(command.dataChunks());
        }

        if (CollectionUtils.isEmpty(chunks)) {
            throw new IllegalInputException(CaseCode.no_chunks_at_source, "No data-chunks at the given source");
        }

        return chunks;
    }

}
