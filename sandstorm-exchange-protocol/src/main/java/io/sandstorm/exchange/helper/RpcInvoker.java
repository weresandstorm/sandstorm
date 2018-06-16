package io.sandstorm.exchange.helper;

import io.grpc.StatusRuntimeException;

import io.sandstorm.exchange.api.msg.ResultMsg;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class RpcInvoker {

    private static final Logger logger = Logger.getLogger(RpcInvoker.class.getName());

    public static void invoke(Callable<ResultMsg> invocation) {
        try {
            ResultMsg result = invocation.call();
            if (result.getCode() != 0) {
                throw new RemoteServiceException(result.getMessage());
            }
        } catch (StatusRuntimeException ex) {
            throw new RpcException(ex);
        } catch (Throwable ex) {
            throw new RpcException("Unexpected error occurred during rpc", ex);
        }
    }

    public static void invokeWithRetry(Callable<ResultMsg> invocation) {
        int tryCount = 0;
        int maxTryTimes = 3;
        long intervalBase = 100;
        boolean interrupted = false;
        final ObjectId invocationId = new ObjectId();

        try {
            while ((++tryCount) <= maxTryTimes) {
                try {
                    RpcInvoker.invoke(invocation);
                    return;
                } catch (RpcException rpcEx) {
                    String message = String.format("Rpc invocation [%s] failed %s times", invocationId, tryCount);
                    logger.log(Level.SEVERE, message, rpcEx);
                    if (tryCount == maxTryTimes) {
                        throw rpcEx;
                    }
                }

                try {
                    long intervalMills = (long) (intervalBase * Math.pow(2, tryCount - 1));
                    Thread.sleep(intervalMills);
                } catch (InterruptedException ex1) {
                    interrupted = true;
                    // fall through and retry
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
