package io.sandstorm.rest;

import io.sandstorm.common.IllegalInputException;
import io.sandstorm.common.InputAssert;
import io.sandstorm.common.query.domain.Existence;
import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.SimplePagingQParams;
import io.sandstorm.controller.app.CheckJobExecsEnded;
import io.sandstorm.controller.app.DataChunkQParams;
import io.sandstorm.controller.app.DataSetApp;
import io.sandstorm.controller.app.DataSetCmd;
import io.sandstorm.controller.app.FindService;
import io.sandstorm.controller.app.LocalTempStorage;
import io.sandstorm.controller.app.TempFile;
import io.sandstorm.controller.domain.resource.DataChunk;
import io.sandstorm.controller.domain.resource.DataSet;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Part;

import static io.sandstorm.common.InputAssert.notBlank;
import static io.sandstorm.common.InputAssert.notNull;

@RestController
@RequestMapping("/data-sets")
public class DataSetRest {

    @Resource
    private DataSetApp dataSetApp;
    @Resource
    private FindService findService;
    @Resource
    private LocalTempStorage localTempStorage;

    @PostMapping("/data-chunks/actions/upload")
    public TempFile uploadDataChunk(@RequestPart("dataChunk") Part dataChunk) {
        InputAssert.notNull(dataChunk, "dataChunk");
        String fileName = FilePartHelper.getFileName(dataChunk);
        if (!fileName.endsWith(".gz")) {
            throw new IllegalInputException("Uploaded file must be gzip format");
        }
        return localTempStorage.put(
                FilePartHelper.getInputStream(dataChunk),
                fileName);
    }

    @PostMapping("/data-chunks/actions/remove-temp-file")
    public void deleteDataChunk(@RequestBody TempFile tempFile) {
        InputAssert.notNull(tempFile, "tempFile in request body");
        tempFile.validate();
        localTempStorage.remove(tempFile);
    }

    @GetMapping("/{feeder_file_name}/actions/check-existence")
    public Existence checkExistence(@PathVariable("feeder_file_name") String feederFileName) {
        notBlank(feederFileName, "path variable feederFileName");
        return dataSetApp.checkExistence(feederFileName);
    }

    @PostMapping
    public void create(@RequestBody DataSetCmd.Create createCmd) {
        notNull(createCmd, "dataSet in request body");
        createCmd.validate();
        dataSetApp.createDataSet(createCmd);
    }

    /**
     * 查看引用给定数据集的所有 job-execs 是否都执行结束
     * @param id
     */
    @GetMapping("/{id}/actions/check_referring_job_execs_ended")
    public CheckJobExecsEnded checkReferringJobExecsEnded(@PathVariable("id") ObjectId id) {
        return dataSetApp.checkReferringJobExecsEnded(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") ObjectId id,
                       @RequestBody DataSetCmd.Update updateCmd) {
        updateCmd.validate();
        dataSetApp.update(updateCmd);
    }

    @GetMapping
    public Page<DataSet> list(SimplePagingQParams params) {
        params.validate();
        return findService.findDataSets(params);
    }

    @GetMapping("/{id}")
    public DataSet detail(@PathVariable("id") ObjectId id) {
        notNull(id, "path variable id");
        return dataSetApp.detail(id);
    }

    @GetMapping("/{id}/data-chunks")
    public Page<DataChunk> listDataChunks(@PathVariable("id") ObjectId dataSetId,
                                          DataChunkQParams params) {
        notNull(dataSetId, "path variable id");
        params.setDataSetId(dataSetId);
        params.validate();
        return findService.findDataChunks(params);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") ObjectId id) {
        notNull(id, "path variable id");
        dataSetApp.delete(id);
    }

}
