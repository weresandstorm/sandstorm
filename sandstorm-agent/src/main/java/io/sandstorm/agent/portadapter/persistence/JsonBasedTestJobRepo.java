package io.sandstorm.agent.portadapter.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.sandstorm.agent.domain.AgentConfig;
import io.sandstorm.agent.domain.LocalTestJobRepo;
import io.sandstorm.agent.domain.model.LocalLoadProfile;
import io.sandstorm.agent.domain.model.LocalTestJob;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonBasedTestJobRepo implements LocalTestJobRepo {

    private static final Logger logger = LoggerFactory.getLogger(JsonBasedTestJobRepo.class);

    private static final Gson gson;
    private static final JsonBasedTestJobRepo instance;
    private static final String testJobStoragePath;
    private static final String loadProfileStoragePath;

    static {
        gson = new GsonBuilder().create();
        instance = new JsonBasedTestJobRepo();
        testJobStoragePath = AgentConfig.testDataRootDir() + File.separator + "TestJob.json";
        loadProfileStoragePath = AgentConfig.testDataRootDir() + File.separator + "LoadProfile.json";
    }

    private JsonBasedTestJobRepo() {
    }

    public static final JsonBasedTestJobRepo getInstance() {
        return instance;
    }

    public static void main(String[] args) {

    }

    @Override
    public synchronized void save(LocalTestJob testJob) {
        try {
            String jsonData = gson.toJson(testJob);
            File jsonStorage = newFileAndMakeParentDirs(testJobStoragePath);
            FileUtils.writeStringToFile(jsonStorage, jsonData, "UTF-8");
            saveLoadProfile(testJob.loadProfile());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save a LocalTestJob into json-based storage", e);
        }
    }

    private void saveLoadProfile(LocalLoadProfile loadProfile) {
        try {
            String jsonData = gson.toJson(loadProfile);
            File jsonStorage = newFileAndMakeParentDirs(loadProfileStoragePath);
            FileUtils.writeStringToFile(jsonStorage, jsonData, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Failed to save a LocalTestJob into json-based storage", e);
        }
    }

    private File newFileAndMakeParentDirs(String filePath) {
        try {
            File theFile = new File(filePath);
            if (!theFile.exists()) {
                FileUtils.touch(theFile);
            }
            return theFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to new a local json storage file", e);
        }
    }
}
