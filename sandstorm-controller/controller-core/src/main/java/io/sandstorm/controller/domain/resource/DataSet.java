package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.domain.model.EntityOrBuilder;
import io.sandstorm.common.domain.model.ValueObject;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;
import java.util.Optional;

/**
 * A data set used to populate load test requests
 */
@Entity(value = "data_sets", noClassnameStored = true)
public class DataSet extends EntityOrBuilder {

    public static final int initial_revision = 0;

    private String feederFileName;
    private String desc;
    private SourceType sourceType;
    private String sourceUrl;
    private int chunksCount;
    private int rev; // revision

    private DataSet() {
    }

    public DataSet(String feederFileName, String desc, SourceType sourceType, int chunksCount, Optional<String> sourceUrl, String creator) {
        this.feederFileName = feederFileName;
        this.desc = desc;
        this.sourceType = sourceType;
        if (sourceType == SourceType.url) {
            this.sourceUrl = sourceUrl.orElse(null);
        }
        this.chunksCount = chunksCount;
        this.creator = creator;
    }

    @Override
    public void beginDomainLifeCycle() {
        super.beginDomainLifeCycle();
        this.rev = initial_revision;
    }

    public void setChunksCount(int chunksCount) {
        this.chunksCount = chunksCount;
    }

    public int chunksCount() {
        return this.chunksCount;
    }

    public String feederFileName() {
        return this.feederFileName;
    }

    public String sourceUrl() {
        return this.sourceUrl;
    }

    public int revision() {
        return this.rev;
    }

    public void mergeFrom(DataSet change, boolean sourceChanged) {
        this.desc = change.desc;
        refreshLastUpdated();
        if (sourceChanged) {
            this.sourceType = change.sourceType;
            this.chunksCount = change.chunksCount;
            this.rev = this.rev + 1;
            if (this.sourceType == SourceType.url) {
                this.sourceUrl = change.sourceUrl;
            }
        }
    }

    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.v_feederFileName(feederFileName);
        visitor.v_desc(desc);
        visitor.v_sourceUrl(sourceUrl);
    }

    public interface Visitor extends EntityOrBuilder.Visitor {
        void v_feederFileName(String feederFileName);

        void v_desc(String desc);

        void v_sourceUrl(String sourceUrl);
    }

    public enum SourceType {
        url, manual_upload
    }

    public static final class Slice implements ValueObject {
        private String feederFileName;
        private List<DataChunk.Traits> chunks;

        private Slice() {
        }

        public Slice(String feederFileName, List<DataChunk.Traits> chunks) {
            this.feederFileName = feederFileName;
            this.chunks = chunks;
        }
    }

}
