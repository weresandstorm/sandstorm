package io.sandstorm.agent.domain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import org.codehaus.plexus.util.StringUtils;

public class AgentConfig {

    private static final String CONFIG_PATH = "config/config.properties";
    private static final String AGENT_PORT = "agent.port";
    private static final String CTRL_HOST = "controller.host";
    private static final String CTRL_PORT = "controller.port";
    private static final String GATLING_HOME = "agent.gatling.startup.home";
    private static final String GATLING_NO_REPORT = "agent.gatling.no.report";
    private static final String GATLING_JAVA_OPTS = "agent.gatling.java-opts";
    private static final String GATLING_JAVA_JMX = "agent.gatling.jmx";
    private static final String TEST_SCRIPT_ROOT_DIR = "test-script.root.dir";
    private static final String TEST_DATA_ROOT_DIR = "test-data.root.dir";
    private static final String LOCAL_JSON_STORAGE = "local.json.storage";
    private static final String OBJS_BUCKET_NAME = "obj-storage.bucket.name";
    private static final String OBJS_PUBLIC_KEY = "obj-storage.public-key";
    private static final String OBJS_PRIVATE_KEY = "obj-storage.private-key";
    private static final String OBJS_PROXY_SUFFIX = "obj-storage.proxy.suffix";
    private static final String OBJS_DOWNLOAD_PROXY_SUFFIX = "obj-storage.download.proxy.suffix";

    private static String testScriptRootDir;
    private static String testDataRootDir;
    private static String reportRootDir;
    private static String localJsonStorage;

    private static Properties properties = loadProperties(CONFIG_PATH);
    private static Map<String, String> env = System.getenv();

    public static Integer agentPort() {
        return Integer.valueOf(properties.getProperty(AGENT_PORT));
    }

    public static String controllerHost() {
        return properties.getProperty(CTRL_HOST);
    }

    public static Integer controllerPort() {
        return Integer.valueOf(properties.getProperty(CTRL_PORT));
    }

    public static String gatlingHome() {
        return properties.getProperty(GATLING_HOME);
    }

    public static Boolean gatlingNoReport() {
        return Boolean.valueOf(properties.getProperty(GATLING_NO_REPORT));
    }

    public static String gatlingJavaOpts() {
        return properties.getProperty(GATLING_JAVA_OPTS);
    }

    public static String gatlingJavaJmx() {
        return properties.getProperty(GATLING_JAVA_JMX);
    }

    public static String testScriptRootDir() {
        return gatlingHome() + File.separator + "script";
    }

    public static String testDataRootDir() {
        if (StringUtils.isEmpty(testDataRootDir)) {
            StringBuilder sb = new StringBuilder(gatlingHome());
            testDataRootDir = sb.append("/testdata").toString();
        }
        return testDataRootDir;
    }

    public static String reportRootDir() {
        if (StringUtils.isEmpty(reportRootDir)) {
            StringBuilder sb = new StringBuilder(gatlingHome());
            reportRootDir = sb.append("/report").toString();
        }
        return reportRootDir;
    }

    public static String localJsonStorage() {
        if (StringUtils.isEmpty(localJsonStorage)) {
            StringBuilder sb = new StringBuilder(gatlingHome());
            localJsonStorage = sb.append("/testdata/TestJob.json").toString();
        }
        return localJsonStorage;
    }

    public static String objSBucketName() {
        return properties.getProperty(OBJS_BUCKET_NAME);
    }

    public static String objSPublicKey() {
        return properties.getProperty(OBJS_PUBLIC_KEY);
    }

    public static String objSPrivateKey() {
        return properties.getProperty(OBJS_PRIVATE_KEY);
    }

    public static String objSProxySuffix() {
        return properties.getProperty(OBJS_PROXY_SUFFIX);
    }

    public static String objSDownloadProxySuffix() {
        return properties.getProperty(OBJS_DOWNLOAD_PROXY_SUFFIX);
    }

    public static String getEnvProperty(String envName) {
        return env.get(envName);
    }

    private static Properties loadProperties(String configPath) {
        InputStream input = AgentConfig.class.getClassLoader().getResourceAsStream(configPath);
        if (input == null) {
            throw new IllegalArgumentException("Configuration file '" + configPath + "' not found on classpath");
        }

        final Properties config = new Properties();
        try {
            config.load(input);
        } catch (IOException e) {
            throw new IllegalArgumentException("Reading configuration from '" + configPath + "' failed", e);
        }
        return config;
    }
}
