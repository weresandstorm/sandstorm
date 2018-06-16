package io.sandstorm.agent.domain;

import io.sandstorm.agent.domain.model.LocalTestJob;
import io.sandstorm.agent.portadapter.persistence.JsonBasedTestJobRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentCoreService {

    private static final Logger logger = LoggerFactory.getLogger(AgentCoreService.class);

    private static AgentCoreService instance = new AgentCoreService();
    private JobResourceService jobResourceService = JobResourceService.getInstance();
    private JobEngineService jobEngineService = JobEngineService.getInstance();
    private LocalTestJobRepo testJobRepo = JsonBasedTestJobRepo.getInstance();

    private AgentCoreService() {
    }

    public static AgentCoreService getInstance() {
        return instance;
    }

    public void beginPreparation(LocalTestJob testJob) {
        logger.info("****** Received the testJob assigned by the Controller ******");
        testJobRepo.save(testJob);
        jobEngineService.initEngine(testJob);
        jobResourceService.initDownloader(testJob);
        jobResourceService.startDownloadResourcesFor(testJob);
    }

    public void startJob(LocalTestJob testJob) {
        logger.info("****** Received the start testJob command from the Controller ******");
        jobEngineService.startEngine(testJob);
    }

    public void stopJob(LocalTestJob testJob) {
        logger.info("****** Received the stop testJob command from the Controller ******");
        jobEngineService.stopEngine(testJob);
    }

}
