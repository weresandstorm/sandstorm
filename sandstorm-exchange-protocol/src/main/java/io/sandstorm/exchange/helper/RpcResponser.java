package io.sandstorm.exchange.helper;

import io.sandstorm.exchange.api.msg.ResultMsg;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class RpcResponser {

    private static final Logger logger = Logger.getLogger(RpcResponser.class.getName());

    private RpcResponser() {
    }

    public static <R> void respond(Callable<R> implInvocation, StreamObserver<ResultMsg> responseObserver) {
        ResultMsg response;
        try {
            R localResult = implInvocation.call();
            response = respForSuccess(localResult);
        } catch (Throwable e) {
            logger.log(Level.SEVERE, "Error occurred while responding to rpc request", e);
            response = respForFailure(e);
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private static ResultMsg respForSuccess(Object response) {
        return ResultMsg.newBuilder().setCode(0).setMessage("ok").build();
    }

    private static ResultMsg respForFailure(Throwable ex) {
        String message = String.format("%s: %s", ex.getClass().getName(), ex.getMessage());
        return ResultMsg.newBuilder().setCode(1).setMessage(message).build();
    }

}
