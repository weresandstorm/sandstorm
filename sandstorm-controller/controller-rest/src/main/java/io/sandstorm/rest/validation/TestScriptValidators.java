package io.sandstorm.rest.validation;

import io.sandstorm.common.ValueChecks;
import io.sandstorm.controller.domain.resource.TestScript;
import org.bson.types.ObjectId;

import static io.sandstorm.common.InputAssert.*;

public final class TestScriptValidators {

    public static final GenericValidator creation_validator = new GenericValidator();
    public static final UpdateValidator update_validator = new UpdateValidator();

    private static final int ALIAS_MAX_LEN = 30;

    private static final int DESC_MAX_LEN = 80;

    private TestScriptValidators() {
    }

    public static class GenericValidator extends BasicValidator implements TestScript.Visitor {
        @Override
        public void v_jarAlias(String jarAlias) {
            assertTrue(
                    (ValueChecks.notBlank(jarAlias)
                            && jarAlias.endsWith(".jar")
                            && ValueChecks.letterNumHyphen_(jarAlias.replace(".jar", ""))
                            && ValueChecks.lenLte(jarAlias, ALIAS_MAX_LEN)),
                    "jarAlias: name must consist of letters, numbers, '_' and '-', extension must be '.jar' and total len should be lte " + ALIAS_MAX_LEN
            );
        }

        @Override
        public void v_desc(String desc) {
            if (ValueChecks.notBlank(desc)) {
                lenLte(desc, "desc", DESC_MAX_LEN);
            }
        }
    }

    public static final class UpdateValidator extends GenericValidator {
        @Override
        public void v_id(ObjectId id) {
            notNull(id, "id");
        }
    }
}
