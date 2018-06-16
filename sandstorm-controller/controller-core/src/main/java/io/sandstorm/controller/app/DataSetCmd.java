package io.sandstorm.controller.app;

import io.sandstorm.common.ValueChecks;
import io.sandstorm.controller.domain.resource.DataSet;
import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.sandstorm.common.InputAssert.*;

public abstract class DataSetCmd {

    private DataSetCmd() {
    }

    public static abstract class CreateOrUpdate {

        private static final int NAME_MAX_LEN = 30;
        private static final int DESC_MAX_LEN = 80;

        private String feederFileName;
        private String desc;
        private DataSet.SourceType sourceType;
        private Optional<String> sourceUrl = Optional.empty();
        private List<TempFile> dataChunks = Collections.emptyList();
        private String operator;

        public String feederFileName() {
            return feederFileName;
        }

        /**
         * This method is for json deserializer.
         *
         * @param sourceUrl
         */
        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = Optional.ofNullable(sourceUrl);
        }

        public String desc() {
            return desc;
        }

        public DataSet.SourceType sourceType() {
            return sourceType;
        }

        public Optional<String> sourceUrl() {
            return sourceUrl;
        }

        public List<TempFile> dataChunks() {
            return dataChunks;
        }

        public String operator() {
            return this.operator;
        }

        public void validate() {
            notBlank(feederFileName, "feederFileName");
            lenLte(feederFileName, "feederFileName", NAME_MAX_LEN);
            fileNameAndExt(feederFileName, "feederFileName");

            if (ValueChecks.notBlank(desc)) {
                lenLte(desc, "desc", DESC_MAX_LEN);
            }

            notBlank(operator, "operator");
        }

        protected void checkSource() {
            notNull(sourceType, "sourceType");
            if (DataSet.SourceType.url == sourceType) {
                notBlank(sourceUrl.orElse(null), "sourceUrl");
            } else {
                notEmpty(dataChunks, "dataChunks");
                for (TempFile tempFile : dataChunks) {
                    tempFile.validate();
                }
            }
        }
    }

    public static class Create extends CreateOrUpdate {
        @Override
        public void validate() {
            super.validate();
            checkSource();
        }
    }

    public static class Update extends CreateOrUpdate {

        private Optional<ObjectId> id = Optional.empty();
        private Boolean contentChanged;

        public void setId(ObjectId id) {
            this.id = Optional.ofNullable(id);
        }

        public ObjectId id() {
            return id.get();
        }

        public boolean contentChanged() {
            return contentChanged;
        }

        public void validate() {
            notNull(id.orElse(null), "id");
            super.validate();
            notNull(contentChanged, "contentChanged");
            if (contentChanged) {
                checkSource();
            }
        }
    }
}
