package io.sandstorm.agent.portadapter.gateway.out;

import io.sandstorm.agent.domain.AgentConfig;
import io.sandstorm.agent.domain.model.MachineInfo;
import io.sandstorm.exchange.api.ControllerServiceGrpc;
import io.sandstorm.exchange.api.msg.JobIdMsg;
import io.sandstorm.exchange.api.msg.LoadInjectorMsg;
import io.sandstorm.exchange.api.msg.ResultMsg;
import io.sandstorm.exchange.api.msg.ResultNotifyMsg;
import io.sandstorm.exchange.helper.GrpcClient;
import io.sandstorm.exchange.helper.RpcException;
import io.sandstorm.exchange.helper.RpcInvoker;
import io.sandstorm.exchange.helper.ServerAddress;

import java.util.concurrent.Callable;
import java.util.logging.Level;

/**
 * A client for controller-service server.
 */
public class ControllerClient extends GrpcClient {

    private static final ControllerClient ctrlClient = new ControllerClient(new ServerAddress(AgentConfig.controllerHost(), AgentConfig.controllerPort()));
    private final ControllerServiceGrpc.ControllerServiceBlockingStub blockingStub;

    private ControllerClient(ServerAddress serverAddress) {
        super(serverAddress);
        blockingStub = ControllerServiceGrpc.newBlockingStub(channel);
    }

    public static ControllerClient getInstance() {
        return ctrlClient;
    }

    public void registerLoadInjector() {
        MachineInfo machineInfo = new MachineInfo();
        machineInfo.loadInfo();
        LoadInjectorMsg loadInjector = LoadInjectorMsg.newBuilder()
            .setHostname(machineInfo.getHostname())
            .setIntranetIp(machineInfo.getLocalIP())
            .setPort(machineInfo.getPort())
            .build();

        doRpcInvokerWithRetry(() -> this.blockingStub.registerLoadInjector(loadInjector));
    }

    public void onPreparationSuccessed(String jobId) {
        JobIdMsg jobIdMsg = JobIdMsg.newBuilder().setId(jobId).build();
        doRpcInvokerWithRetry(() -> this.blockingStub.onPreparationSucceeded(jobIdMsg));
    }

    public void onPreparationFailed(String jobId, String errorMsg) {
        doRpcInvokerNoRetry(() -> {
            ResultNotifyMsg resultNotifyMsg = ResultNotifyMsg.newBuilder()
                .setId(jobId)
                .setResult(ResultMsg.newBuilder().setMessage(errorMsg).build())
                .build();
            return this.blockingStub.onPreparationFailed(resultNotifyMsg);
        });
    }

    public void onJobCompleted(String jobId) {
        JobIdMsg jobIdMsg = JobIdMsg.newBuilder().setId(jobId).build();
        doRpcInvokerWithRetry(() -> this.blockingStub.onJobCompleted(jobIdMsg));
    }

    public void onJobFailed(String jobId, String errorMsg) {
        doRpcInvokerNoRetry(() -> {
            ResultNotifyMsg resultNotifyMsg = ResultNotifyMsg.newBuilder()
                .setId(jobId)
                .setResult(ResultMsg.newBuilder().setMessage(errorMsg).build())
                .build();
            return this.blockingStub.onJobFailed(resultNotifyMsg);
        });
    }

    private void doRpcInvokerNoRetry(Callable<ResultMsg> invocation) {
        RpcInvoker.invoke(invocation);
    }

    private void doRpcInvokerWithRetry(Callable<ResultMsg> invocation) {
        int tryTimes = 0;
        int retryIntervalBase = 100;
        boolean interrupted = false;
        try {
            while ((++tryTimes) <= 3) {
                try {
                    doRpcInvokerNoRetry(invocation);
                    return;
                } catch (RpcException rpcException) {
                    logger.log(Level.WARNING, "****** Remote invoker controller failed ******\n" +
                            "****** retry {0} times ******\n {1}",
                        new Object[] {tryTimes, rpcException.getMessage()});
                    try {
                        long intervalMills = (long) (retryIntervalBase * Math.pow(2, tryTimes - 1));
                        Thread.sleep(intervalMills);
                    } catch (InterruptedException ex1) {
                        interrupted = true;
                        // fall through and retry
                    }
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }

    }

}
