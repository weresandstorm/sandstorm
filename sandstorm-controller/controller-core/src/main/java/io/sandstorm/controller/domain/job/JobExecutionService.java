package io.sandstorm.controller.domain.job;

import io.sandstorm.controller.domain.resource.LoadInjectorService;
import io.sandstorm.spi.SpiServiceFinder;
import io.sandstorm.spi.report.ReportService;
import io.sandstorm.controller.domain.resource.LoadInjectorService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class JobExecutionService {

    private static final Logger logger = LoggerFactory.getLogger(JobExecutionService.class);

    @Resource
    private JobOperator jobOperator;
    @Resource
    private JobExecRepo jobExecRepo;
    @Resource
    private JobSliceExecRepo sliceExecRepo;
    @Resource
    private LoadInjectorService injectorService;

    private ReportService reportService = SpiServiceFinder.findService(ReportService.class);

    public void prepareFor(ObjectId jobExecId) {
        doActionsToJobExecution(
                jobExecId,
                jobExec -> jobExec.prepare(),
                sliceExec -> sliceExec.prepare()
        );
    }

    public final void startTestEngine(ObjectId jobExecId) {
        doActionsToJobExecution(
                jobExecId,
                jobExec -> jobExec.startTestEngine(),
                sliceExec -> sliceExec.startTestEngine()
        );
    }

    public void stop(ObjectId jobExecId) {
        doActionsToJobExecution(
                jobExecId,
                jobExec -> jobExec.stop(),
                sliceExec -> (!sliceExec.isEnded()),
                sliceExec -> sliceExec.stop()
        );
    }

    public void onSliceExecPreparationSucceeded(ObjectId sliceExecId) {
        JobSliceExecution sliceExec = sliceExecRepo.get(sliceExecId);
        handleEventOnJobSliceExec(sliceExec, theSliceExec -> theSliceExec.onPreparationSucceeded());
        JobExecCtxKeeper.lightPreparedIndicatorIfRunCtxPresent(
                sliceExec.jobExecId(),
                () -> logger.info("Preparation for slice-exec [id:{}] succeeded, so the prepared-indicator "
                                + "inside run-ctx [jobExecId:{}] was lighted (i.e the latch was counted down).",
                        sliceExec.id(), sliceExec.jobExecId()),
                () -> logger.info("When preparation for slice-exec [id:{}] succeeded, "
                                + "the run-ctx [jobExecId:{}] had already been removed!",
                        sliceExec.id(), sliceExec.jobExecId())
        );
    }

    public void onSliceExecPreparationFailed(final ObjectId sliceExecId, final Throwable exception) {
        final JobSliceExecution sliceExec = sliceExecRepo.get(sliceExecId);
        handleEventOnJobSliceExec(sliceExec, theSliceExec -> theSliceExec.onPreparationFailed(exception.getMessage()));
        // Release the use right of the load-injector
        injectorService.handleEventOnInjector(sliceExec.injector().id(), injector -> injector.onUseRightReleased());

        // Attention, please! Don't light the prepared-indicator here.
        // Because the prepared-indicator indicates when all the slice-exec preparations succeeded
        // rather than when all the slice-exec preparations ended (succeeded or failed).

        JobExecCtxKeeper.lightEndedIndicatorIfRunCtxPresent(
                sliceExec.jobExecId(),
                () -> logger.info("Preparation for slice-exec [id:{}] failed, so the ended-indicator "
                                + "inside run-ctx [jobExecId:{}] was lighted (i.e the latch was counted down).",
                        sliceExec.id(), sliceExec.jobExecId()),
                () -> logger.info("When preparation of slice-exec [id:{}] failed, "
                                + "the run-ctx [jobExecId:{}] had already been removed!",
                        sliceExec.id(), sliceExec.jobExecId())
        );

        // Only stop the not-ended slice-execs, so the current slice-exec is excluded
        jobOperator.stop(sliceExec.jobExecId(), filterEndedOut(sliceExec.jobExecId()));
    }

    public void onJobExecPreparationTimeouted(final ObjectId jobExecId, final Throwable exception) {
        doActionOnJobExec(jobExecId, jobExec -> jobExec.recordFailureInfo(exception.getMessage()));
        jobOperator.stop(jobExecId, filterEndedOut(jobExecId));
    }

    public void onJobExecPreparationSucceeded(ObjectId jobExecId) {
        doActionOnJobExec(jobExecId, jobExec -> jobExec.onPreparationSucceeded());
    }

    public void onSliceExecCompleted(ObjectId sliceExecId) {
        JobSliceExecution sliceExec = sliceExecRepo.get(sliceExecId);
        handleEventOnJobSliceExec(sliceExec, theSliceExec -> theSliceExec.onExecutionCompleted());
        // Release the use right of the load-injector
        injectorService.handleEventOnInjector(sliceExec.injector().id(), injector -> injector.onUseRightReleased());

        JobExecCtxKeeper.lightEndedIndicatorIfRunCtxPresent(
                sliceExec.jobExecId(),
                () -> logger.info("Execution of slice-exec [id:{}] completed, so the ended-indicator"
                                + "inside run-ctx [jobExecId:{}] was lighted (i.e the latch was counted down).",
                        sliceExec.id(), sliceExec.jobExecId()),
                () -> logger.info("When execution of slice-exec [id:{}] completed, "
                                + "the run-ctx [jobExecId:{}] had already been removed!",
                        sliceExec.id(), sliceExec.jobExecId())
        );
    }

    public void onSliceExecFailed(final ObjectId sliceExecId, final Throwable exception) {
        final JobSliceExecution sliceExec = sliceExecRepo.get(sliceExecId);
        handleEventOnJobSliceExec(sliceExec, theSliceExec -> theSliceExec.onExecutionFailed(exception.getMessage()));

        // Release the use right of the load-injector
        injectorService.handleEventOnInjector(sliceExec.injector().id(), injector -> injector.onUseRightReleased());

        JobExecCtxKeeper.lightEndedIndicatorIfRunCtxPresent(
                sliceExec.jobExecId(),
                () -> logger.info("Execution of slice-exec [id:{}] failed, so the ended-indicator"
                                + "inside run-ctx [jobExecId:{}] was lighted (i.e the latch was counted down).",
                        sliceExec.id(), sliceExec.jobExecId()),
                () -> logger.info("When execution of slice-exec [id:{}] failed, "
                                + "the run-ctx [jobExecId:{}] had already been removed!",
                        sliceExec.id(), sliceExec.jobExecId())
        );

        // Only stop the not-ended slice-execs, so the current slice-exec is excluded
        jobOperator.stop(sliceExec.jobExecId(), filterEndedOut(sliceExec.jobExecId()));
    }

    public void onJobExecTimeouted(final ObjectId jobExecId, final Throwable exception) {
        doActionOnJobExec(jobExecId, jobExec -> jobExec.recordFailureInfo(exception.getMessage()));
        jobOperator.stop(jobExecId, filterEndedOut(jobExecId));
    }

    public void onJobExecEnded(ObjectId jobExecId) {
        // Generate report asynchronously
        CompletableFuture.runAsync(() -> {
            String reportUrl = reportService.generateReport(jobExecId.toHexString());
            doActionOnJobExec(jobExecId, jobExec -> jobExec.onReportGenerated(reportUrl));
            logger.info("Successfully generated report for job-exec [id:{}]", jobExecId);
        }).exceptionally(ex -> {
            logger.error(ex.getMessage(), ex);
            return null;
        });

        logger.info("Before setting the status of job-exec [id:{}] to some ended status", jobExecId);

        // If any slice-exec is failed, then mark the job-exec as failed;
        // if any slice-exec is stopping, then the job-exec must be currently stopping, keep the status unchanged;
        // if all slice-execs are completed, then mark the job-exec as completed;
        // else all slice-execs are stopped, then mark the job-exec as stopped.
        JobExecution jobExec = jobExecRepo.get(jobExecId);
        ExecutionStatus endedStatus;
        if (sliceExecRepo.countByStatus(jobExecId, ExecutionStatus.failed) > 0) {
            jobExec.onPreparationOrExecFailed();
            endedStatus = ExecutionStatus.failed;
        } else if (sliceExecRepo.countByStatus(jobExecId, ExecutionStatus.stopping) > 0) {
            jobExec.onStopFailed();
            endedStatus = ExecutionStatus.stopping;
        } else if (sliceExecRepo.countByStatus(jobExecId, ExecutionStatus.completed)
                == jobExec.slicesCount()) {
            jobExec.onExecutionCompleted();
            endedStatus = ExecutionStatus.completed;
        } else {
            jobExec.onStopSucceeded();
            endedStatus = ExecutionStatus.stopped;
        }
        jobExecRepo.save(jobExec);
        logger.info("Set the status of job-exec [id:{}] to the ended status {}", jobExecId, endedStatus);

        JobExecCtxKeeper.removeRunCtxAndCancelInnerTasks(jobExecId);
    }

    public void onSliceExecStopSucceeded(ObjectId sliceExecId) {
        JobSliceExecution sliceExec = sliceExecRepo.get(sliceExecId);
        handleEventOnJobSliceExec(sliceExec, theSliceExec -> theSliceExec.onStopSucceeded());
        // Release the use right of the load-injector
        injectorService.handleEventOnInjector(sliceExec.injector().id(), injector -> injector.onUseRightReleased());

        JobExecCtxKeeper.getStopCtx(sliceExec.jobExecId()).lightStoppedIndicator();
        logger.info("Stop of slice-exec [id:{}] succeeded, so the stopped-indicator"
                        + "inside stop-ctx [jobExecId:{}] was lighted (i.e the latch was counted down).",
                sliceExec.id(), sliceExec.jobExecId());

        JobExecCtxKeeper.lightEndedIndicatorIfRunCtxPresent(
                sliceExec.jobExecId(),
                () -> logger.info("Stop of slice-exec [id:{}] succeeded, so the ended-indicator"
                                + "inside run-ctx [jobExecId:{}] was lighted (i.e the latch was counted down).",
                        sliceExec.id(), sliceExec.jobExecId()),
                () -> logger.info("When stop of slice-exec [id:{}] succeeded, "
                                + "the run-ctx [jobExecId:{}] had already been removed!",
                        sliceExec.id(), sliceExec.jobExecId())
        );
    }

    public void onSliceExecStopFailed(ObjectId jobSliceExecId, final Throwable exception) {
        final JobSliceExecution sliceExec = sliceExecRepo.get(jobSliceExecId);
        handleEventOnJobSliceExec(sliceExec, theSliceExec -> theSliceExec.onStopFailed(exception.getMessage()));

        // Release the use right of the load-injector even if stop failed because
        // if the test-engine on the injector is still running, the running process
        // may be killed manually by the user, and if that doesn't happened, it will be stopped
        // by the agent when this agent accepts a new job.
        injectorService.handleEventOnInjector(sliceExec.injector().id(), injector -> injector.onUseRightReleased());

        JobExecCtxKeeper.getStopCtx(sliceExec.jobExecId()).lightStoppedIndicator();
        logger.info("Stop of slice-exec [id:{}] failed, so the stopped-indicator"
                        + "inside stop-ctx [jobExecId:{}] was lighted (i.e the latch was counted down).",
                sliceExec.id(), sliceExec.jobExecId());

        JobExecCtxKeeper.lightEndedIndicatorIfRunCtxPresent(
                sliceExec.jobExecId(),
                () -> logger.info("Stop of slice-exec [id:{}] failed, so the ended-indicator"
                                + "inside run-ctx [jobExecId:{}] was lighted (i.e the latch was counted down).",
                        sliceExec.id(), sliceExec.jobExecId()),
                () -> logger.info("When stop of slice-exec [id:{}] failed, "
                                + "the run-ctx [jobExecId:{}] had already been removed!",
                        sliceExec.id(), sliceExec.jobExecId())
        );
    }

    public void onStopTimeouted(final ObjectId jobExecId, final Throwable exception) {
        List<JobSliceExecution> sliceExecs = sliceExecRepo.findBy(jobExecId, ExecutionStatus.stopping);
        sliceExecs.forEach(sliceExec -> onSliceExecStopFailed(sliceExec.id(), exception));
    }

    public void onJobExecStopEnded(ObjectId jobExecId) {
        JobExecCtxKeeper.removeStopCtx(jobExecId);
    }

    private void handleEventOnJobSliceExec(JobSliceExecution sliceExec, Consumer<JobSliceExecution> eventHandling) {
        sliceExecRepo.getAndChangeThenSave(sliceExec.id(), eventHandling);
    }

    private void doActionOnJobExec(ObjectId jobExecId, Consumer<JobExecution> action) {
        jobExecRepo.getAndChangeThenSave(jobExecId, action);
    }

    private List<JobSliceExecution> filterEndedOut(ObjectId jobExecId) {
        List<JobSliceExecution> sliceExecs = sliceExecRepo.findBy(jobExecId);
        return sliceExecs.stream()
                // Exclude the ended JobSliceExecutions
                .filter(item -> (!item.isEnded()))
                .collect(Collectors.toList());
    }

    private void doActionsToJobExecution(ObjectId jobExecId,
                                         Consumer<JobExecution> actionToJobExec,
                                         Consumer<JobSliceExecution> actionToSliceExec) {
        jobExecRepo.getAndChangeThenSave(jobExecId, actionToJobExec);
        List<JobSliceExecution> sliceExecs = sliceExecRepo.findBy(jobExecId);
        List<ObjectId> sliceExecIds = sliceExecs.stream()
                .map(sliceExec -> sliceExec.id()).collect(Collectors.toList());
        sliceExecRepo.getAndChangeThenSave(sliceExecIds, actionToSliceExec);
    }

    private void doActionsToJobExecution(ObjectId jobExecId,
                                         Consumer<JobExecution> actionToJobExec,
                                         Predicate<JobSliceExecution> predicate,
                                         Consumer<JobSliceExecution> actionToSliceExec) {
        jobExecRepo.getAndChangeThenSave(jobExecId, actionToJobExec);
        List<JobSliceExecution> sliceExecs = sliceExecRepo.findBy(jobExecId);
        List<ObjectId> sliceExecIds = sliceExecs.stream()
                .filter(predicate)
                .map(sliceExec -> sliceExec.id()).collect(Collectors.toList());
        sliceExecRepo.getAndChangeThenSave(sliceExecIds, actionToSliceExec);
    }

}