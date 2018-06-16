package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.SimplePagingQParams;
import io.sandstorm.common.query.domain.Sort;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataSetService {

    @Resource
    private DataChunkRepo dataChunkRepo;

    @Resource
    private DataChunkDiscardRepo dataChunkDiscardRepo;

    public void discardDataChunks(ObjectId dataSetId, String operator) {
        SimplePagingQParams params = new SimplePagingQParams();
        params.setPageSize(200);
        params.setPageNo(1);
        params.setValToMatch(dataSetId.toHexString());
        params.setSort(new Sort(Sort.Direction.ASC, "id"));

        Page<DataChunk> dataChunkPage = dataChunkRepo.findPage(params);
        while (!CollectionUtils.isEmpty(dataChunkPage.items())) {
            List<DataChunkDiscard> dataChunksDiscards = dataChunkPage.items()
                    .stream()
                    .map(dataChunk -> new DataChunkDiscard(dataChunk, operator))
                    .collect(Collectors.toList());
            dataChunkDiscardRepo.save(dataChunksDiscards);

            params.setPageNo(params.pageNo() + 1);
            dataChunkPage = dataChunkRepo.findPage(params);
        }

        dataChunkRepo.deleteByDataSet(dataSetId);
    }

}
