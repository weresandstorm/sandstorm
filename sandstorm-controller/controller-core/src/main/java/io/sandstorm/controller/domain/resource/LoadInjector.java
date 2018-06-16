package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.domain.model.EntityOrBuilder;
import io.sandstorm.common.domain.model.VersionedEntityOrBuilder;
import org.mongodb.morphia.annotations.Entity;

/**
 * A {@link LoadInjector} represents a physical host, virtual host or container
 * on which a slice of a {@link io.sandstorm.controller.domain.job.TestJob} will be executing.
 */
@Entity(value = "load_injectors", noClassnameStored = true)
public class LoadInjector extends VersionedEntityOrBuilder {

    private String hostname;

    private Integer port;

    private String intranetIp;

    private String publicIp;

    private State state;

    private LoadInjector() {
        setSysAsCreator();
    }

    public LoadInjector(String hostname, Integer port, String intranetIp, String publicIp) {
        this.hostname = hostname;
        this.port = port;
        this.intranetIp = intranetIp;
        this.publicIp = publicIp;
        this.state = State.usable;
        setSysAsCreator();
    }

    @Override
    public void beginDomainLifeCycle() {
        this.state = State.usable;
        super.beginDomainLifeCycle();
    }

    public boolean isUsable() {
        return (state == State.usable);
    }

    public void onUseRightReleased() {
        this.state = State.usable;
        this.refreshLastUpdated();
    }

    public void mergeFrom(LoadInjector other) {
        this.hostname = other.hostname;
        this.port = other.port;
        this.intranetIp = other.intranetIp;
        this.publicIp = other.publicIp;
        this.refreshLastUpdated();
    }

    public String hostname() {
        return this.hostname;
    }

    public String intranetIp() {
        return this.intranetIp;
    }

    public Integer port() {
        return this.port;
    }

    public void accept(Visitor visitor) {
        super.accept(visitor);
        visitor.v_hostname(hostname);
        visitor.v_port(port);
        visitor.v_intranetIp(intranetIp);
        visitor.v_publicIp(publicIp);
    }

    public enum State {
        usable, in_use
    }

    public interface Visitor extends EntityOrBuilder.Visitor {
        void v_hostname(String hostname);

        void v_port(Integer port);

        void v_intranetIp(String intranetIp);

        void v_publicIp(String publicIp);
    }

}