package io.sandstorm.controller.portadapter.gateway.in;

import io.sandstorm.controller.domain.job.JobExecutionService;
import io.sandstorm.controller.domain.resource.LoadInjector;
import io.sandstorm.controller.domain.resource.LoadInjectorService;
import io.sandstorm.exchange.api.ControllerServiceGrpc;
import io.sandstorm.exchange.api.msg.JobIdMsg;
import io.sandstorm.exchange.api.msg.LoadInjectorMsg;
import io.sandstorm.exchange.api.msg.ResultMsg;
import io.sandstorm.exchange.api.msg.ResultNotifyMsg;
import io.grpc.stub.StreamObserver;
import io.sandstorm.exchange.helper.RemoteServiceException;
import io.sandstorm.exchange.helper.RpcResponser;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ControllerService extends ControllerServiceGrpc.ControllerServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(ControllerService.class);

    @Resource
    private LoadInjectorService loadInjectorService;
    @Resource
    private JobExecutionService jobExecService;

    @Override
    public void registerLoadInjector(LoadInjectorMsg request, StreamObserver<ResultMsg> responseObserver) {
        logger.info("Received request for registering load-injector [hostname:{}, intranetIp:{}]",
                request.getHostname(), request.getIntranetIp());
        RpcResponser.respond(() -> {
            LoadInjector loadInjector = new LoadInjector(
                    request.getHostname(),
                    request.getPort(),
                    request.getIntranetIp(),
                    request.getPublicIp()
            );
            loadInjectorService.register(loadInjector);
            return null;
        }, responseObserver);
    }

    @Override
    public void onPreparationSucceeded(JobIdMsg jobId, StreamObserver<ResultMsg> responseObserver) {
        logger.info("Received notification-request of preparation succeeded for JobSliceExecution [id:{}]",
                jobId.getId());
        RpcResponser.respond(() -> {
            jobExecService.onSliceExecPreparationSucceeded(toObjectId(jobId.getId()));
            return null;
        }, responseObserver);
    }

    @Override
    public void onPreparationFailed(ResultNotifyMsg resultNotify, StreamObserver<ResultMsg> responseObserver) {
        logger.info("Received notification-request of preparation failed for JobSliceExecution [id:{}]",
                resultNotify.getId());
        RpcResponser.respond(() -> {
            RemoteServiceException ex = toException(resultNotify.getResult());
            jobExecService.onSliceExecPreparationFailed(toObjectId(resultNotify.getId()), ex);
            return null;
        }, responseObserver);
    }

    @Override
    public void onJobCompleted(JobIdMsg jobId, StreamObserver<ResultMsg> responseObserver) {
        logger.info("Received notification-request of execution completed for JobSliceExecution [id:{}]",
                jobId.getId());
        RpcResponser.respond(() -> {
            jobExecService.onSliceExecCompleted(toObjectId(jobId.getId()));
            return null;
        }, responseObserver);
    }

    @Override
    public void onJobFailed(ResultNotifyMsg resultNotify, StreamObserver<ResultMsg> responseObserver) {
        logger.info("Received notification-request of execution failed for JobSliceExecution [id:{}]",
                resultNotify.getId());
        RpcResponser.respond(() -> {
            RemoteServiceException ex = toException(resultNotify.getResult());
            jobExecService.onSliceExecFailed(toObjectId(resultNotify.getId()), ex);
            return null;
        }, responseObserver);
    }

    private ObjectId toObjectId(String id) {
        return new ObjectId(id);
    }

    private RemoteServiceException toException(ResultMsg resultMsg) {
        return new RemoteServiceException(
                String.format("Error reported by agent: code: %s, message: %s",
                        resultMsg.getCode(),
                        resultMsg.getMessage())
        );
    }

}
