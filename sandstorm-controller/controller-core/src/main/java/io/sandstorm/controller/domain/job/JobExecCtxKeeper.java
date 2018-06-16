package io.sandstorm.controller.domain.job;

import org.apache.commons.lang3.mutable.MutableBoolean;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class JobExecCtxKeeper {

    private static final Logger logger = LoggerFactory.getLogger(JobExecCtxKeeper.class);

    /**
     * The key is {@link JobExecution#id}, which is also the identifier of a {@link RunCtx}.
     */
    private static final Map<ObjectId, RunCtx> runCtxs = new ConcurrentHashMap<>(10);

    /**
     * The key is {@link JobExecution#id}, which is also the identifier of a {@link StopCtx}.
     */
    private static final Map<ObjectId, StopCtx> stopCtxs = new ConcurrentHashMap<>(10);

    private JobExecCtxKeeper() {
    }

    public static boolean initRunCtx(ObjectId jobExecId, final Future startTask, final Future waitEndedTask, final int slicesCount) {
        final MutableBoolean successful = new MutableBoolean(false);
        runCtxs.computeIfAbsent(
                jobExecId,
                theJobExecId -> {
                    successful.setTrue();
                    return new RunCtx(theJobExecId, startTask, waitEndedTask, slicesCount);
                });
        return successful.getValue();
    }

    public static Optional<RunCtx> getRunCtx(ObjectId jobExecId) {
        RunCtx taskCtx = runCtxs.get(jobExecId);
        return Optional.ofNullable(taskCtx);
    }

    /**
     * Light the prepared-indicator if the {@link RunCtx} identified by given {@code jobExecId} is present.
     * Otherwise execute given action {@code actionIfAbsent}.
     *
     * @param jobExecId       the identifier of the targeted context
     * @param actionIfPresent the action to execute if the ctx is present
     * @param actionIfAbsent  the action to execute if the ctx is absent
     */
    static void lightPreparedIndicatorIfRunCtxPresent(ObjectId jobExecId, Runnable actionIfPresent, Runnable actionIfAbsent) {
        Optional<RunCtx> ctx = JobExecCtxKeeper.getRunCtx(jobExecId);
        if (ctx.isPresent()) {
            ctx.get().lightPreparedIndicator();
            actionIfPresent.run();
        } else {
            actionIfAbsent.run();
        }
    }

    /**
     * Light the ended-indicator if the {@link RunCtx} identified by given {@code jobExecId} is present.
     * Otherwise execute given action {@code actionIfAbsent}.
     *
     * @param jobExecId       the identifier of the targeted context
     * @param actionIfPresent the action to execute if the ctx is present
     * @param actionIfAbsent  the action to execute if the ctx is absent
     */
    static void lightEndedIndicatorIfRunCtxPresent(ObjectId jobExecId, Runnable actionIfPresent, Runnable actionIfAbsent) {
        Optional<RunCtx> ctx = JobExecCtxKeeper.getRunCtx(jobExecId);
        if (ctx.isPresent()) {
            ctx.get().lightEndedIndicator();
            actionIfPresent.run();
        } else {
            actionIfAbsent.run();
        }
    }

    /**
     * Remove the {@link RunCtx} identified by given {@code jobExecId}, and cancel the tasks inside the ctx.
     * This method should be called only once.
     *
     * @param jobExecId the identifier of a {@link RunCtx}
     */
    public static void removeRunCtxAndCancelInnerTasks(ObjectId jobExecId) {
        RunCtx ctx = runCtxs.remove(jobExecId);
        if (ctx == null) {
            throw new JobExecutionException(String.format(
                    "run-ctx [jobExecId:%s] should be present when this method is called, "
                            + "maybe because you called this method twice or there is a unknown bug",
                    jobExecId));
        }

        if (ctx.startTask.cancel(true)) {
            logger.info("Successfully cancelled the start-task [jobExecId:{}]", jobExecId);
        } else {
            logger.error("Failed to cancel the start-task [jobExecId:{}]", jobExecId);
        }

        // Here, it's unnecessary to cancel the wait-ended-task inside the removed run-ctx.
        // Because the current call to this method is just executed in the wait-ended-task,
        // and this suggests that the wait-ended-task doesn't block on any operation and it will
        // definitely exit.
    }

    /**
     * Create a {@link StopCtx} with the given params, and put the {@link StopCtx} into internal cache
     * with given {@code jobExecId} as key.
     *
     * @param jobExecId   the key of internal cache entry
     * @param stopTask
     * @param slicesCount
     * @return true if the key is absent and initialization is ok; otherwise false.
     */
    public static boolean initStopCtx(ObjectId jobExecId, final Future stopTask, final int slicesCount) {
        final MutableBoolean successful = new MutableBoolean(false);
        stopCtxs.computeIfAbsent(
                jobExecId,
                theJobExecId -> {
                    successful.setTrue();
                    return new StopCtx(theJobExecId, stopTask, slicesCount);
                });
        return successful.getValue();
    }

    public static StopCtx getStopCtx(ObjectId jobExecId) {
        StopCtx taskCtx = stopCtxs.get(jobExecId);
        assert (taskCtx != null);
        return taskCtx;
    }

    /**
     * Remove the {@link StopCtx} identified by given {@code jobExecId}.
     * This method should be called only once.
     *
     * @param jobExecId the identifier of a {@link StopCtx}
     */
    public static void removeStopCtx(ObjectId jobExecId) {
        StopCtx ctx = stopCtxs.remove(jobExecId);
        if (ctx == null) {
            throw new JobExecutionException(String.format(
                    "stop-ctx [jobExecId:%s] should be present when this method is called, "
                            + "maybe because you called this method twice or there is a unknown bug",
                    jobExecId));
        }

        // Here, it's unnecessary to cancel the stop-task inside the removed stop-ctx.
        // Because the current call to this method is just executed in the stop-task,
        // and this suggests that the stop-task doesn't block on any operation and it will
        // definitely exit.
    }

    public static final class RunCtx extends IndicatorWaiter {
        private final ObjectId jobExecId;
        private final Future startTask;
        private final Future waitEndedTask;
        private final CountDownLatch preparedIndicator;
        private final CountDownLatch endedIndicator;

        private RunCtx(ObjectId jobExecId, Future startTask, Future waitEndedTask, int slicesCount) {
            this.jobExecId = jobExecId;
            this.startTask = startTask;
            this.waitEndedTask = waitEndedTask;
            this.preparedIndicator = new CountDownLatch(slicesCount);
            this.endedIndicator = new CountDownLatch(slicesCount);
        }

        public void lightPreparedIndicator() {
            preparedIndicator.countDown();
        }

        public boolean waitPreparedIndicatorFullyLighted(long timeoutSecs) {
            return waitOn(preparedIndicator, timeoutSecs);
        }

        public void lightEndedIndicator() {
            endedIndicator.countDown();
        }

        public boolean waitEndedIndicatorFullyLighted(long timeoutSecs) {
            return waitOn(endedIndicator, timeoutSecs);
        }
    }

    public static final class StopCtx extends IndicatorWaiter {
        private final ObjectId jobExecId;
        private final Future stopTask;
        private final CountDownLatch stoppedIndicator;

        private StopCtx(ObjectId jobExecId, Future stopTask, int slicesCount) {
            this.jobExecId = jobExecId;
            this.stopTask = stopTask;
            this.stoppedIndicator = new CountDownLatch(slicesCount);
        }

        public void lightStoppedIndicator() {
            stoppedIndicator.countDown();
        }

        public boolean waitStoppedIndicatorFullyLighted(long timeoutSecs) {
            return waitOn(stoppedIndicator, timeoutSecs);
        }
    }

    private static abstract class IndicatorWaiter {
        protected final boolean waitOn(CountDownLatch indicator, long timeoutSecs) {
            boolean interrupted = false;
            try {
                long remainingTime = timeoutSecs;
                long startMillis = System.currentTimeMillis();
                while (true) {
                    try {
                        return indicator.await(remainingTime, TimeUnit.SECONDS);
                    } catch (InterruptedException ex) {
                        interrupted = true;
                        remainingTime = timeoutSecs - (System.currentTimeMillis() - startMillis) / 1000;
                        logger.warn("Interrupted waiting on indicator (CountDownLatch: {})", indicator.getCount());
                    }
                }
            } finally {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
