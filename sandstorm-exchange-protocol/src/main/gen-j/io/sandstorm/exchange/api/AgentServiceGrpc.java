package io.sandstorm.exchange.api;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.12.0)",
    comments = "Source: agent_service.proto")
public final class AgentServiceGrpc {

  private AgentServiceGrpc() {}

  public static final String SERVICE_NAME = "AgentService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getPrepareForMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> METHOD_PREPARE_FOR = getPrepareForMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getPrepareForMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getPrepareForMethod() {
    return getPrepareForMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getPrepareForMethodHelper() {
    io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg, io.sandstorm.exchange.api.msg.ResultMsg> getPrepareForMethod;
    if ((getPrepareForMethod = AgentServiceGrpc.getPrepareForMethod) == null) {
      synchronized (AgentServiceGrpc.class) {
        if ((getPrepareForMethod = AgentServiceGrpc.getPrepareForMethod) == null) {
          AgentServiceGrpc.getPrepareForMethod = getPrepareForMethod = 
              io.grpc.MethodDescriptor.<io.sandstorm.exchange.api.msg.TestJobMsg, io.sandstorm.exchange.api.msg.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "AgentService", "PrepareFor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.TestJobMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new AgentServiceMethodDescriptorSupplier("PrepareFor"))
                  .build();
          }
        }
     }
     return getPrepareForMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStartTestEngineMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> METHOD_START_TEST_ENGINE = getStartTestEngineMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getStartTestEngineMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getStartTestEngineMethod() {
    return getStartTestEngineMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getStartTestEngineMethodHelper() {
    io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg, io.sandstorm.exchange.api.msg.ResultMsg> getStartTestEngineMethod;
    if ((getStartTestEngineMethod = AgentServiceGrpc.getStartTestEngineMethod) == null) {
      synchronized (AgentServiceGrpc.class) {
        if ((getStartTestEngineMethod = AgentServiceGrpc.getStartTestEngineMethod) == null) {
          AgentServiceGrpc.getStartTestEngineMethod = getStartTestEngineMethod = 
              io.grpc.MethodDescriptor.<io.sandstorm.exchange.api.msg.TestJobMsg, io.sandstorm.exchange.api.msg.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "AgentService", "StartTestEngine"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.TestJobMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new AgentServiceMethodDescriptorSupplier("StartTestEngine"))
                  .build();
          }
        }
     }
     return getStartTestEngineMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStopMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> METHOD_STOP = getStopMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getStopMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getStopMethod() {
    return getStopMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getStopMethodHelper() {
    io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.TestJobMsg, io.sandstorm.exchange.api.msg.ResultMsg> getStopMethod;
    if ((getStopMethod = AgentServiceGrpc.getStopMethod) == null) {
      synchronized (AgentServiceGrpc.class) {
        if ((getStopMethod = AgentServiceGrpc.getStopMethod) == null) {
          AgentServiceGrpc.getStopMethod = getStopMethod = 
              io.grpc.MethodDescriptor.<io.sandstorm.exchange.api.msg.TestJobMsg, io.sandstorm.exchange.api.msg.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "AgentService", "Stop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.TestJobMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new AgentServiceMethodDescriptorSupplier("Stop"))
                  .build();
          }
        }
     }
     return getStopMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AgentServiceStub newStub(io.grpc.Channel channel) {
    return new AgentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AgentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AgentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AgentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AgentServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AgentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void prepareFor(io.sandstorm.exchange.api.msg.TestJobMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getPrepareForMethodHelper(), responseObserver);
    }

    /**
     */
    public void startTestEngine(io.sandstorm.exchange.api.msg.TestJobMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getStartTestEngineMethodHelper(), responseObserver);
    }

    /**
     */
    public void stop(io.sandstorm.exchange.api.msg.TestJobMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getStopMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPrepareForMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.sandstorm.exchange.api.msg.TestJobMsg,
                io.sandstorm.exchange.api.msg.ResultMsg>(
                  this, METHODID_PREPARE_FOR)))
          .addMethod(
            getStartTestEngineMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.sandstorm.exchange.api.msg.TestJobMsg,
                io.sandstorm.exchange.api.msg.ResultMsg>(
                  this, METHODID_START_TEST_ENGINE)))
          .addMethod(
            getStopMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.sandstorm.exchange.api.msg.TestJobMsg,
                io.sandstorm.exchange.api.msg.ResultMsg>(
                  this, METHODID_STOP)))
          .build();
    }
  }

  /**
   */
  public static final class AgentServiceStub extends io.grpc.stub.AbstractStub<AgentServiceStub> {
    private AgentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AgentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AgentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AgentServiceStub(channel, callOptions);
    }

    /**
     */
    public void prepareFor(io.sandstorm.exchange.api.msg.TestJobMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPrepareForMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startTestEngine(io.sandstorm.exchange.api.msg.TestJobMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartTestEngineMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stop(io.sandstorm.exchange.api.msg.TestJobMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStopMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AgentServiceBlockingStub extends io.grpc.stub.AbstractStub<AgentServiceBlockingStub> {
    private AgentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AgentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AgentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AgentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.sandstorm.exchange.api.msg.ResultMsg prepareFor(io.sandstorm.exchange.api.msg.TestJobMsg request) {
      return blockingUnaryCall(
          getChannel(), getPrepareForMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public io.sandstorm.exchange.api.msg.ResultMsg startTestEngine(io.sandstorm.exchange.api.msg.TestJobMsg request) {
      return blockingUnaryCall(
          getChannel(), getStartTestEngineMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public io.sandstorm.exchange.api.msg.ResultMsg stop(io.sandstorm.exchange.api.msg.TestJobMsg request) {
      return blockingUnaryCall(
          getChannel(), getStopMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AgentServiceFutureStub extends io.grpc.stub.AbstractStub<AgentServiceFutureStub> {
    private AgentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AgentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AgentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AgentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.sandstorm.exchange.api.msg.ResultMsg> prepareFor(
        io.sandstorm.exchange.api.msg.TestJobMsg request) {
      return futureUnaryCall(
          getChannel().newCall(getPrepareForMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.sandstorm.exchange.api.msg.ResultMsg> startTestEngine(
        io.sandstorm.exchange.api.msg.TestJobMsg request) {
      return futureUnaryCall(
          getChannel().newCall(getStartTestEngineMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.sandstorm.exchange.api.msg.ResultMsg> stop(
        io.sandstorm.exchange.api.msg.TestJobMsg request) {
      return futureUnaryCall(
          getChannel().newCall(getStopMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PREPARE_FOR = 0;
  private static final int METHODID_START_TEST_ENGINE = 1;
  private static final int METHODID_STOP = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AgentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AgentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PREPARE_FOR:
          serviceImpl.prepareFor((io.sandstorm.exchange.api.msg.TestJobMsg) request,
              (io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg>) responseObserver);
          break;
        case METHODID_START_TEST_ENGINE:
          serviceImpl.startTestEngine((io.sandstorm.exchange.api.msg.TestJobMsg) request,
              (io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg>) responseObserver);
          break;
        case METHODID_STOP:
          serviceImpl.stop((io.sandstorm.exchange.api.msg.TestJobMsg) request,
              (io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AgentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AgentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.sandstorm.exchange.api.AgentServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AgentService");
    }
  }

  private static final class AgentServiceFileDescriptorSupplier
      extends AgentServiceBaseDescriptorSupplier {
    AgentServiceFileDescriptorSupplier() {}
  }

  private static final class AgentServiceMethodDescriptorSupplier
      extends AgentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AgentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AgentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AgentServiceFileDescriptorSupplier())
              .addMethod(getPrepareForMethodHelper())
              .addMethod(getStartTestEngineMethodHelper())
              .addMethod(getStopMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
