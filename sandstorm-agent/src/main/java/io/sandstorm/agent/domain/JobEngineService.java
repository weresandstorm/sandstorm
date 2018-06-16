package io.sandstorm.agent.domain;

import io.sandstorm.agent.domain.model.LocalTestJob;
import io.sandstorm.agent.portadapter.gateway.out.ControllerClient;
import io.sandstorm.agent.portadapter.gatling.GatlingExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.apache.commons.exec.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobEngineService {

    private static final Logger logger = LoggerFactory.getLogger(JobEngineService.class);

    private static JobEngineService instance = new JobEngineService();
    private GatlingExecutor gatlingExecutor = GatlingExecutor.getInstance();
    private ControllerClient ctrlClient = ControllerClient.getInstance();

    public JobEngineService() {
    }

    public static JobEngineService getInstance() {
        return instance;
    }

    public void initEngine(LocalTestJob testJob) {
        try {
            this.gatlingExecutor.stopJob();
        } catch (Exception ignore) {
        }
    }

    public void startEngine(LocalTestJob testJob) {
        logger.info("****** Started launching gatling asynchronous service ******");
        try {
            this.gatlingExecutor.startJob(testJob, new LoadTestCallBack(testJob));
        } catch (Exception e) {
            this.onJobExecutedException(testJob, e);
        }
    }

    public void stopEngine(LocalTestJob jobExec) {
        try {
            this.gatlingExecutor.stopJob();
        } catch (Exception e) {
            throw e;
        }
    }

    private void onJobExecutedException(LocalTestJob testJob, Exception ex) {
        ctrlClient.onJobFailed(testJob.id(), ex.getMessage());
        logger.info("****** Gatling asynchronous service are Failed ******");
    }

    private void onJobExecutedSucceeded(LocalTestJob testJob) {
        ctrlClient.onJobCompleted(testJob.id());
        logger.info("****** Gatling asynchronous service are completed ******");
    }

    public class LoadTestCallBack implements ExecuteResultHandler {

        /** the interval polling the result */
        private static final int SLEEP_TIME_MS = 50;

        /** Keep track if the process is still running */
        private volatile boolean hasResult;

        /** The exit value of the finished process */
        private volatile int exitValue = Executor.INVALID_EXITVALUE;

        /** Any offending exception */
        private volatile ExecuteException exception;

        private LocalTestJob testJob;

        public LoadTestCallBack(LocalTestJob testJob) {
            this.testJob = testJob;
        }

        @Override
        public void onProcessComplete(int exitValue) {
            this.exitValue = exitValue;
            this.exception = null;
            this.hasResult = true;
            JobEngineService.this.onJobExecutedSucceeded(this.testJob);
        }

        @Override
        public void onProcessFailed(ExecuteException e) {
            this.exitValue = e.getExitValue();
            this.exception = e;
            this.hasResult = true;
            String errorMsg = String.format("The process exexuted has an error [%s], testJob is [%s]", this.exception.getMessage(), testJob);
            logger.error(errorMsg, e);
            JobEngineService.this.onJobExecutedException(this.testJob, e);
        }

        public ExecuteException getException() {
            if (!this.hasResult) {
                throw new IllegalStateException("The process has not exited yet therefore no result is available ...");
            }
            return this.exception;
        }

        public int getExitValue() {
            if (!this.hasResult) {
                throw new IllegalStateException("The process has not exited yet therefore no result is available ...");
            }
            return this.exitValue;
        }

        public boolean hasResult() {
            return this.hasResult;
        }

        /**
         * Causes the current thread to wait, if necessary, until the process has terminated. This method returns
         * immediately if the process has already terminated. If the process has not yet terminated, the calling thread
         * will be blocked until the process exits.
         *
         * @throws InterruptedException if the current thread is {@linkplain Thread#interrupt() interrupted} by another
         * thread while it is waiting, then the wait is ended and an {@link InterruptedException} is thrown.
         */
        public int waitFor() throws InterruptedException {
            while (!hasResult()) {
                Thread.sleep(this.SLEEP_TIME_MS);
            }
            return this.exitValue;
        }

        public int waitFor(final long timeout) throws InterruptedException {
            final long until = System.currentTimeMillis() + timeout;
            while (!hasResult() && (System.currentTimeMillis() < until)) {
                Thread.sleep(this.SLEEP_TIME_MS);
            }
            return this.exitValue;
        }
    }
}
