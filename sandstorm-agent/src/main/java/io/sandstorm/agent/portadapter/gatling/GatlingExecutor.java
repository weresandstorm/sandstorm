package io.sandstorm.agent.portadapter.gatling;

import io.sandstorm.agent.domain.JobEngineService.LoadTestCallBack;
import io.sandstorm.agent.domain.JobExecuteException;
import io.sandstorm.agent.domain.model.LocalTestJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatlingExecutor {

    private static final Logger logger = LoggerFactory.getLogger(GatlingExecutor.class);
    private static GatlingExecutor instance = new GatlingExecutor();
    private LocalTestJob executedJob;
    private GatlingTask gatlingTask;

    private GatlingExecutor() {
    }

    public static GatlingExecutor getInstance() {
        return instance;
    }

    public void startJob(LocalTestJob testJob, LoadTestCallBack callback) {
        try {
            this.executedJob = testJob;
            gatlingTask = new GatlingTask(executedJob, callback);
            gatlingTask.startExecute();
        } catch (Exception e) {
            String errMsg = String.format("Gatling Started failed, the testJob is [%s], errorMsg is [%s]", executedJob, e.getMessage());
            logger.error(errMsg, e);
            throw new JobExecuteException(errMsg, e);
        }
    }

    public void stopJob() {
        if (gatlingTask != null) {
            try {
                gatlingTask.stopExecute();
                gatlingTask = null;
                executedJob = null;
            } catch (Exception e) {
                String errMsg = String.format("Gatling Stopped failed, the testJob is [%s], errorMsg is [%s]", executedJob, e.getMessage());
                logger.error(errMsg, e);
                throw new JobExecuteException(errMsg, e);
            }
        }
    }

    public LocalTestJob getExecutedJob() {
        return executedJob;
    }
}
