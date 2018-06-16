package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.domain.model.EntityOrBuilder;
import org.mongodb.morphia.annotations.Entity;

import java.util.Date;

@Entity(value = "test_script_discards", noClassnameStored = true)
public class TestScriptDiscard extends EntityOrBuilder {

    private TestScript script;
    private Date discardedAt;

    private TestScriptDiscard() {
    }

    public TestScriptDiscard(TestScript testScript, String creator) {
        this.script = testScript;
        this.discardedAt = new Date();
        this.creator = creator;
        beginDomainLifeCycle();
    }

}
