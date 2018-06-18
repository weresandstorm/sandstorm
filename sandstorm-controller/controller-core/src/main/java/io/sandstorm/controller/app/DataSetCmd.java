package io.sandstorm.controller.app;

import static io.sandstorm.common.InputAssert.notBlank;
import static io.sandstorm.common.InputAssert.notNull;

import com.fasterxml.jackson.core.type.TypeReference;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.DataSet.SourceType;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.json.JSONObject;

public abstract class DataSetCmd {

    private DataSetCmd() {
    }

    public static abstract class CreateOrUpdate extends JSONObject {

        public String feederFileName(){
            return getString("feederFileName");
        }
        public String desc(){
            return getString("desc");
        }
        public Optional<String> sourceUrl(){
            return Optional.of(getString("sourceUrl"));
        }
        public SourceType sourceType(){
            return getEnum(SourceType.class, "sourceType");
        }
        public String operator(){
            return getString("operator");
        }
        public List<TempFile> dataChunks(){
            TypeReference<List<TempFile>> ref = new TypeReference() { };
            return CommandConverter.convert(getJSONObject("dataChunks"),ref);
        }

        protected void checkSource() {
            if (DataSet.SourceType.url == sourceType()) {
                notBlank(sourceUrl().orElse(null), "sourceUrl");
            } else {
                notNull(dataChunks(), "dataChunks");
            }
        }
    }

    public static class Create extends CreateOrUpdate {

        public void validate(){
            checkSource();
        }
    }

    public static class Update extends CreateOrUpdate {


        public ObjectId id() {
            return new ObjectId(getString("id"));
        }

        public boolean contentChanged() {
            return getBoolean("contentChanged");
        }

        public void validate() {
            if (contentChanged()) {
                checkSource();
            }
        }
    }
}
