package io.sandstorm.agent.portadapter.gatling;

import io.sandstorm.agent.domain.AgentConfig;
import java.util.Collections;
import java.util.List;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Arrays.asList;

public final class GatlingConfig {

    public static final String STARTUP_HOME = AgentConfig.gatlingHome();
    public static final String DATA_DIR = AgentConfig.testDataRootDir();
    public static final String REPORT_DIR = AgentConfig.reportRootDir();
    public static final boolean NO_REPORT = AgentConfig.gatlingNoReport();
    public static final String STARTUP_JAR_NAME = "gatling-startup.jar";
    public static final String STARTUP_MAIN_CLASS = "io.sandstorm.gatling.GatlingStartup";
    private static final Logger logger = LoggerFactory.getLogger(GatlingConfig.class);
    final static List<String> DEFAULT_JAVA_OPTS;
    final static List<String> JAVA_JMX_OPTS;

    static {
        String defaultJavaOps = AgentConfig.gatlingJavaOpts();
        if (StringUtils.isEmpty(defaultJavaOps)) {
            DEFAULT_JAVA_OPTS = Collections.EMPTY_LIST;
        } else {
            DEFAULT_JAVA_OPTS = asList(defaultJavaOps.split(","));
        }
        String defaultJavaJmx = AgentConfig.gatlingJavaJmx();
        if (StringUtils.isEmpty(defaultJavaJmx)) {
            JAVA_JMX_OPTS = Collections.EMPTY_LIST;
        } else {
            JAVA_JMX_OPTS = asList(defaultJavaJmx.split(","));
        }
    }
}
