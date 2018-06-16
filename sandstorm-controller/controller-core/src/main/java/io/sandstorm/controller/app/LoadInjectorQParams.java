package io.sandstorm.controller.app;

import io.sandstorm.common.query.domain.Pageable;
import io.sandstorm.controller.domain.resource.LoadInjector;

import java.util.Optional;

public class LoadInjectorQParams extends Pageable {

    private Optional<LoadInjector.State> state = Optional.empty();
    private Optional<String> ip = Optional.empty();

    public void setState(LoadInjector.State state) {
        this.state = Optional.ofNullable(state);
    }

    public void setIp(String ip) {
        this.ip = Optional.ofNullable(ip);
    }

    public Optional<LoadInjector.State> state() {
        return this.state;
    }

    public Optional<String> ip() {
        return this.ip;
    }

}
