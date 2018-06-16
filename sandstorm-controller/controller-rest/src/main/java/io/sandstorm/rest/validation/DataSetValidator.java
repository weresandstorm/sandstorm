package io.sandstorm.rest.validation;

import io.sandstorm.common.ValueChecks;
import io.sandstorm.controller.domain.resource.DataSet;

import static io.sandstorm.common.InputAssert.*;

public class DataSetValidator extends BasicValidator implements DataSet.Visitor {

    public static final DataSetValidator object = new DataSetValidator();

    private static final int NAME_MAX_LEN = 30;
    private static final int DESC_MAX_LEN = 80;

    private DataSetValidator() {}

    @Override
    public void v_feederFileName(String feederFileName) {
        String valueDesc = "feederFileName";
        notBlank(feederFileName, valueDesc);
        lenLte(feederFileName, valueDesc, NAME_MAX_LEN);
        fileNameAndExt(feederFileName, valueDesc);
    }

    @Override
    public void v_desc(String desc) {
        if (ValueChecks.notNull(desc)) {
            lenLte(desc, "desc", DESC_MAX_LEN);
        }
    }

    @Override
    public void v_sourceUrl(String sourceUrl) {
        notBlank(sourceUrl, "sourceUrl");
    }
}
