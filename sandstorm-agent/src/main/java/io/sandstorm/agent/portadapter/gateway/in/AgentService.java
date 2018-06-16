package io.sandstorm.agent.portadapter.gateway.in;

import io.sandstorm.agent.domain.AgentCoreService;
import io.sandstorm.agent.domain.model.LocalTestJob;
import io.sandstorm.exchange.api.msg.ResultMsg;
import io.sandstorm.exchange.api.msg.TestJobMsg;
import io.grpc.stub.StreamObserver;
import io.sandstorm.exchange.api.AgentServiceGrpc;
import io.sandstorm.exchange.helper.RpcResponser;

public class AgentService extends AgentServiceGrpc.AgentServiceImplBase {

    private AgentCoreService coreService = AgentCoreService.getInstance();

    @Override
    public void prepareFor(TestJobMsg testJob, StreamObserver<ResultMsg> responseObserver) {
        RpcResponser.respond(() -> {
            LocalTestJob localTestJob = TestJobTranslator.toLocalTestJob(testJob);
            coreService.beginPreparation(localTestJob);
            return null;
        }, responseObserver);
    }

    @Override
    public void startTestEngine(TestJobMsg testJob, StreamObserver<ResultMsg> responseObserver) {
        RpcResponser.respond(() -> {
            LocalTestJob localTestJob = TestJobTranslator.toLocalTestJob(testJob);
            coreService.startJob(localTestJob);
            return null;
        }, responseObserver);
    }

    @Override
    public void stop(TestJobMsg testJob, StreamObserver<ResultMsg> responseObserver) {
        RpcResponser.respond(() -> {
            LocalTestJob localTestJob = TestJobTranslator.toLocalTestJob(testJob);
            coreService.stopJob(localTestJob);
            return null;
        }, responseObserver);
    }
}

