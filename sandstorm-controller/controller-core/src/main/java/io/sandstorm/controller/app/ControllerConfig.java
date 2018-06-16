package io.sandstorm.controller.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ControllerConfig {

    private static String testScriptRootPath;

    private static String testDataFileRootPath;

    @Value("${test-script.root.path}")
    public void setTestScriptRootPath(String storageTestScriptRootPath) {
        ControllerConfig.testScriptRootPath = storageTestScriptRootPath;
    }

    @Value("${test-data-file.root.path}")
    public void setTestDataFileRootPath(String storageTestDataFileRootPath) {
        ControllerConfig.testDataFileRootPath = storageTestDataFileRootPath;
    }

    public static String testScriptRootPath() {
        return ControllerConfig.testScriptRootPath;
    }

    public static String testDataFileRootPath() {
        return ControllerConfig.testDataFileRootPath;
    }

}
