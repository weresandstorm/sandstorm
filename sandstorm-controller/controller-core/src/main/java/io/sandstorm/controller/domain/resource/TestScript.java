package io.sandstorm.controller.domain.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.sandstorm.common.domain.model.EntityOrBuilder;
import io.sandstorm.common.presentation.Views;
import org.mongodb.morphia.annotations.Entity;

/**
 * A jar archive that contains one or more simulations.
 */
@Entity(value = "test_scripts", noClassnameStored = true)
public class TestScript extends EntityOrBuilder {

    @JsonView(Views.Rest.class)
    protected String jarAlias;

    @JsonView(Views.Rest.class)
    protected String jarUrl;

    @JsonView(Views.Rest.class)
    protected String desc;

    protected int rev; // revision

    @Override
    public void beginDomainLifeCycle() {
        super.beginDomainLifeCycle();
        this.rev = 0;
    }

    public String jarAlias() {
        return this.jarAlias;
    }

    public void setJarUrl(String jarUrl) {
        this.jarUrl = jarUrl;
    }

    public void mergeFrom(TestScript change, boolean contentChanged) {
        desc = change.desc;
        refreshLastUpdated();
        if (contentChanged) {
            this.jarUrl = change.jarUrl;
            this.rev = this.rev + 1;
        }
    }

    public int revision() {
        return rev;
    }

    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.v_jarAlias(jarAlias);
        visitor.v_desc(desc);
    }

    public interface Visitor extends EntityOrBuilder.Visitor {

        void v_jarAlias(String jarAlias);

        void v_desc(String desc);

    }

}
