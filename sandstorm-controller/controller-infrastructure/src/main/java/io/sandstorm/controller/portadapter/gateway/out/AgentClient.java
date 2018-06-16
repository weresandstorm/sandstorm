package io.sandstorm.controller.portadapter.gateway.out;

import io.sandstorm.exchange.api.AgentServiceGrpc;
import io.sandstorm.exchange.api.msg.TestJobMsg;
import io.sandstorm.exchange.helper.GrpcClient;
import io.sandstorm.exchange.helper.RpcInvoker;
import io.sandstorm.exchange.helper.ServerAddress;

/**
 * A client for the agent-service server.
 */
public class AgentClient extends GrpcClient {

    private final AgentServiceGrpc.AgentServiceBlockingStub blockingStub;

    public AgentClient(ServerAddress serverAddress) {
        super(serverAddress);
        blockingStub = AgentServiceGrpc.newBlockingStub(channel);
    }

    public void prepareFor(TestJobMsg job) {
        RpcInvoker.invoke(() -> blockingStub.prepareFor(job));
    }

    public void startTestEngine(TestJobMsg job) {
        RpcInvoker.invoke(() -> blockingStub.startTestEngine(job));
    }

    public void stop(TestJobMsg job) {
        RpcInvoker.invoke(() -> blockingStub.stop(job));
    }

}
