package io.sandstorm.controller.domain.job;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JobOperator {

    private static final Logger logger = LoggerFactory.getLogger(JobOperator.class);

    @Resource
    private JobExecutionService jobExecService;
    @Resource
    private AgentService agentService;

    private final ExceptionLogger exceptionLogger = new ExceptionLogger();

    private final long prepareTimeoutSecs = 90;
    private final long stopTimeoutSecs = 90;

    private final ExecutorService jobExecutor = Executors.newCachedThreadPool(new ThreadFactory() {
        private final AtomicLong no = new AtomicLong(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "Job-Worker-" + no.incrementAndGet());
            t.setUncaughtExceptionHandler(exceptionLogger);
            t.setDaemon(true);
            return t;
        }
    });

    private final ExecutorService futureExecutor = Executors.newCachedThreadPool(new ThreadFactory() {
        private final AtomicLong no = new AtomicLong(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "Future-Runner-" + no.incrementAndGet());
            t.setUncaughtExceptionHandler(exceptionLogger);
            t.setDaemon(true);
            return t;
        }
    });

    public void run(final JobExecution jobExec, List<JobSliceExecution> jobSliceExecs) {
        final FutureTask startTask = new FutureTask(() -> {
            start(jobExec, jobSliceExecs);
            return null;
        });
        final FutureTask waitExecEndedTask = new FutureTask(() -> {
            waitJobExecEnded(jobExec);
            return null;
        });

        boolean successful = JobExecCtxKeeper.initRunCtx(
                jobExec.id(),
                startTask,
                waitExecEndedTask,
                jobSliceExecs.size());
        if (successful) {
            jobExecutor.execute(startTask);
            jobExecutor.execute(waitExecEndedTask);
        }
    }

    public void stop(ObjectId jobExecId, final List<JobSliceExecution> sliceExecsToStop) {
        if (CollectionUtils.isEmpty(sliceExecsToStop)) {
            return;
        }

        FutureTask stopTask = new FutureTask(() -> {
            doStop(jobExecId, sliceExecsToStop);
            return null;
        });
        boolean successful = JobExecCtxKeeper.initStopCtx(jobExecId, stopTask, sliceExecsToStop.size());
        if (successful) {
            jobExecutor.execute(stopTask);
        }
    }

    private void start(JobExecution jobExec, List<JobSliceExecution> jobSliceExecs) {
        jobExecService.prepareFor(jobExec.id());
        prepareFor(jobSliceExecs);

        // The precondition here is calling JobExecCtxKeeper.initRunCtx before getting it.
        // That is ensured, so it's unnecessary to check if the RunCtx is present.
        boolean preparationSucceeded = JobExecCtxKeeper
                .getRunCtx(jobExec.id())
                .get()
                .waitPreparedIndicatorFullyLighted(prepareTimeoutSecs);
        if (!preparationSucceeded) {
            logger.info("Preparation for job-exec [id:{}] timeouted", jobExec.id());
            jobExecService.onJobExecPreparationTimeouted(jobExec.id(), new JobExecutionException("Preparation timeouted"));
            return;
        }

        logger.info("Preparation for job-exec [id:{}] succeeded", jobExec.id());
        jobExecService.onJobExecPreparationSucceeded(jobExec.id());
        jobExecService.startTestEngine(jobExec.id());
        startTestEngine(jobSliceExecs);
    }

    private void waitJobExecEnded(JobExecution jobExec) {
        long totalRunTimeEstimate = prepareTimeoutSecs + jobExec.loadProfile().totalExecutionTime();
        // The precondition here is calling JobExecCtxKeeper.initRunCtx before getting it.
        // That is ensured, so it's unnecessary to check if the RunCtx is present.
        boolean execEnded = JobExecCtxKeeper
                .getRunCtx(jobExec.id())
                .get()
                .waitEndedIndicatorFullyLighted(totalRunTimeEstimate);
        if (! execEnded) {
            logger.info("Execution of job-exec [id:{}] timeouted", jobExec.id());
            jobExecService.onJobExecTimeouted(jobExec.id(), new JobExecutionException("Execution timeouted"));
            // Attention please! Don't return here.
        }

        // Whether the wait-ended-task timeouted or not, the following statements should be executed.
        logger.info("Execution of job-exec [id:{}] ended", jobExec.id());
        jobExecService.onJobExecEnded(jobExec.id());
    }

    private void doStop(ObjectId jobExecId, List<JobSliceExecution> jobSliceExecs) {
        jobExecService.stop(jobExecId);
        stop(jobSliceExecs);

        // The precondition here is calling JobExecCtxKeeper.initStopCtx before getting it.
        // That is ensured, so it's unnecessary to check if the StopCtx is present.
        boolean stopEnded = JobExecCtxKeeper
                .getStopCtx(jobExecId)
                .waitStoppedIndicatorFullyLighted(stopTimeoutSecs);
        if (!stopEnded) {
            logger.info("Stop of job-exec [id:{}] timeouted", jobExecId);
            jobExecService.onStopTimeouted(jobExecId, new JobExecutionException("Stop timeouted"));
            // Attention please! Don't return here.
        }

        // Whether the stop-task timeouted or not, the following statements should be executed.
        logger.info("Stop of job-exec [id:{}] ended", jobExecId);
        jobExecService.onJobExecStopEnded(jobExecId);
    }

    private void prepareFor(List<JobSliceExecution> sliceExecs) {
        sliceExecs.stream()
                .map(sliceExec -> new CallCtx().setSliceExec(sliceExec))
                .map(callCtx -> {
                    CompletableFuture<Void> callFuture = CompletableFuture.runAsync(
                            () -> agentService.prepareFor(callCtx.sliceExec),
                            futureExecutor
                    );
                    return callCtx.setResultFuture(callFuture);
                })
                .map(callCtx -> {
                    CompletableFuture<Void> successFuture = callCtx.<Void>resultFuture().thenAcceptAsync(
                            result -> logger.info(
                                    "Request to prepare for slice-exec [id:{}, jobExecId:{}] succeeded",
                                    callCtx.sliceExec.id(),
                                    callCtx.sliceExec.jobExecId()),
                            futureExecutor
                    );
                    return callCtx.setResultFuture(successFuture);
                })
                .map(callCtx -> {
                    CompletableFuture<Void> failureFuture = callCtx.<Void>resultFuture().exceptionally(
                            (ex) -> {
                                logger.error(String.format(
                                        "Request to prepare for slice-exec [id:%s, jobExecId:%s] failed",
                                        callCtx.sliceExec.id(),
                                        callCtx.sliceExec.jobExecId()), ex);
                                jobExecService.onSliceExecPreparationFailed(callCtx.sliceExec.id(), ex);
                                return null;
                            }
                    );
                    return callCtx.setResultFuture(failureFuture);
                })
                .map(callCtx -> callCtx.resultFuture.join())
                .count();
    }

    private void startTestEngine(List<JobSliceExecution> jobSliceExecs) {
        jobSliceExecs.stream()
                .map(sliceExec -> new CallCtx().setSliceExec(sliceExec))
                .map(callCtx -> {
                    CompletableFuture<Void> callFuture = CompletableFuture.runAsync(
                            () -> agentService.startTestEngine(callCtx.sliceExec),
                            futureExecutor
                    );
                    return callCtx.setResultFuture(callFuture);
                })
                .map(callCtx -> {
                    CompletableFuture<Void> successFuture = callCtx.<Void>resultFuture().thenAcceptAsync(
                            result -> logger.info(
                                    "Request to start test-engine for slice-exec [id:{}, jobExecId:{}] succeeded",
                                    callCtx.sliceExec.id(),
                                    callCtx.sliceExec.jobExecId()),
                            futureExecutor
                    );
                    return callCtx.setResultFuture(successFuture);
                })
                .map(callCtx -> {
                    CompletableFuture<Void> failureFuture = callCtx.<Void>resultFuture().exceptionally(
                            (ex) -> {
                                logger.error(String.format(
                                        "Request to start test-engine for slice-exec [id:%s, jobExecId:%s] failed",
                                        callCtx.sliceExec.id(), callCtx.sliceExec.jobExecId()), ex);
                                jobExecService.onSliceExecFailed(callCtx.sliceExec.id(), ex);
                                return null;
                            }
                    );
                    return callCtx.setResultFuture(failureFuture);
                })
                .map(callCtx -> callCtx.resultFuture.join())
                .count();
    }

    private void stop(List<JobSliceExecution> jobSliceExecs) {
        jobSliceExecs.stream()
                .map(sliceExec -> new CallCtx().setSliceExec(sliceExec))
                .map(callCtx -> {
                    CompletableFuture<Void> callFuture = CompletableFuture.runAsync(
                            () -> agentService.stop(callCtx.sliceExec),
                            futureExecutor
                    );
                    return callCtx.setResultFuture(callFuture);
                })
                .map(callCtx -> {
                    CompletableFuture<Void> successFuture = callCtx.<Void>resultFuture().thenAcceptAsync(
                            result -> {
                                logger.info(
                                        "Stop of slice-exec [id:{}, jobExecId:{}] succeeded",
                                        callCtx.sliceExec.id(),
                                        callCtx.sliceExec.jobExecId());
                                jobExecService.onSliceExecStopSucceeded(callCtx.sliceExec.id());
                            },
                            futureExecutor
                    );
                    return callCtx.setResultFuture(successFuture);
                })
                .map(callCtx -> {
                    CompletableFuture<Void> failureFuture = callCtx.<Void>resultFuture().exceptionally(
                            ex -> {
                                logger.error(String.format(
                                        "Stop of slice-exec [id:%s, jobExecId:%s] failed",
                                        callCtx.sliceExec.id(),
                                        callCtx.sliceExec.jobExecId()), ex);
                                jobExecService.onSliceExecStopFailed(callCtx.sliceExec.id(), ex);
                                return null;
                            }
                    );
                    return callCtx.setResultFuture(failureFuture);
                })
                .map(callCtx -> callCtx.resultFuture.join())
                .count();
    }

    private static final class CallCtx {
        private JobSliceExecution sliceExec;
        private CompletableFuture resultFuture;

        public CallCtx setSliceExec(JobSliceExecution sliceExec) {
            this.sliceExec = sliceExec;
            return this;
        }

        public CallCtx setResultFuture(CompletableFuture resultFuture) {
            this.resultFuture = resultFuture;
            return this;
        }

        public <R> CompletableFuture<R> resultFuture() {
            return (CompletableFuture<R>) resultFuture;
        }
    }

    private static final class ExceptionLogger implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

}
