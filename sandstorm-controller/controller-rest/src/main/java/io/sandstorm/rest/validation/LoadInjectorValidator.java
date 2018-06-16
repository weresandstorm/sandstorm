package io.sandstorm.rest.validation;

import io.sandstorm.controller.domain.resource.LoadInjector;

import static io.sandstorm.common.InputAssert.*;

public class LoadInjectorValidator extends BasicValidator implements LoadInjector.Visitor {

    public static final LoadInjectorValidator object = new LoadInjectorValidator();

    private LoadInjectorValidator() {
    }

    @Override
    public void v_hostname(String hostname) {
        notBlank(hostname, "hostname");
    }

    @Override
    public void v_port(Integer port) {
        notNull(port, "port");
        gte(port, "port", 0);
    }

    @Override
    public void v_intranetIp(String intranetIp) {
        notBlank(intranetIp, "intranetIp");
        ipv4(intranetIp, "intranetIp");
    }

    @Override
    public void v_publicIp(String publicIp) {
        notBlank(publicIp, "publicIp");
        ipv4(publicIp, "publicIp");
    }
}
