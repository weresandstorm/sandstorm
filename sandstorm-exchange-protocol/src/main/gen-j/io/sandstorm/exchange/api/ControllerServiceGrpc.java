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
    comments = "Source: controller_service.proto")
public final class ControllerServiceGrpc {

  private ControllerServiceGrpc() {}

  public static final String SERVICE_NAME = "ControllerService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getRegisterLoadInjectorMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.LoadInjectorMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> METHOD_REGISTER_LOAD_INJECTOR = getRegisterLoadInjectorMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.LoadInjectorMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getRegisterLoadInjectorMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.LoadInjectorMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getRegisterLoadInjectorMethod() {
    return getRegisterLoadInjectorMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.LoadInjectorMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getRegisterLoadInjectorMethodHelper() {
    io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.LoadInjectorMsg, io.sandstorm.exchange.api.msg.ResultMsg> getRegisterLoadInjectorMethod;
    if ((getRegisterLoadInjectorMethod = ControllerServiceGrpc.getRegisterLoadInjectorMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getRegisterLoadInjectorMethod = ControllerServiceGrpc.getRegisterLoadInjectorMethod) == null) {
          ControllerServiceGrpc.getRegisterLoadInjectorMethod = getRegisterLoadInjectorMethod = 
              io.grpc.MethodDescriptor.<io.sandstorm.exchange.api.msg.LoadInjectorMsg, io.sandstorm.exchange.api.msg.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ControllerService", "RegisterLoadInjector"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.LoadInjectorMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("RegisterLoadInjector"))
                  .build();
          }
        }
     }
     return getRegisterLoadInjectorMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getOnPreparationSucceededMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> METHOD_ON_PREPARATION_SUCCEEDED = getOnPreparationSucceededMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnPreparationSucceededMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnPreparationSucceededMethod() {
    return getOnPreparationSucceededMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnPreparationSucceededMethodHelper() {
    io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg, io.sandstorm.exchange.api.msg.ResultMsg> getOnPreparationSucceededMethod;
    if ((getOnPreparationSucceededMethod = ControllerServiceGrpc.getOnPreparationSucceededMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getOnPreparationSucceededMethod = ControllerServiceGrpc.getOnPreparationSucceededMethod) == null) {
          ControllerServiceGrpc.getOnPreparationSucceededMethod = getOnPreparationSucceededMethod = 
              io.grpc.MethodDescriptor.<io.sandstorm.exchange.api.msg.JobIdMsg, io.sandstorm.exchange.api.msg.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ControllerService", "OnPreparationSucceeded"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.JobIdMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("OnPreparationSucceeded"))
                  .build();
          }
        }
     }
     return getOnPreparationSucceededMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getOnPreparationFailedMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> METHOD_ON_PREPARATION_FAILED = getOnPreparationFailedMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnPreparationFailedMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnPreparationFailedMethod() {
    return getOnPreparationFailedMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnPreparationFailedMethodHelper() {
    io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg, io.sandstorm.exchange.api.msg.ResultMsg> getOnPreparationFailedMethod;
    if ((getOnPreparationFailedMethod = ControllerServiceGrpc.getOnPreparationFailedMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getOnPreparationFailedMethod = ControllerServiceGrpc.getOnPreparationFailedMethod) == null) {
          ControllerServiceGrpc.getOnPreparationFailedMethod = getOnPreparationFailedMethod = 
              io.grpc.MethodDescriptor.<io.sandstorm.exchange.api.msg.ResultNotifyMsg, io.sandstorm.exchange.api.msg.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ControllerService", "OnPreparationFailed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultNotifyMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("OnPreparationFailed"))
                  .build();
          }
        }
     }
     return getOnPreparationFailedMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getOnJobCompletedMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> METHOD_ON_JOB_COMPLETED = getOnJobCompletedMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnJobCompletedMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnJobCompletedMethod() {
    return getOnJobCompletedMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnJobCompletedMethodHelper() {
    io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.JobIdMsg, io.sandstorm.exchange.api.msg.ResultMsg> getOnJobCompletedMethod;
    if ((getOnJobCompletedMethod = ControllerServiceGrpc.getOnJobCompletedMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getOnJobCompletedMethod = ControllerServiceGrpc.getOnJobCompletedMethod) == null) {
          ControllerServiceGrpc.getOnJobCompletedMethod = getOnJobCompletedMethod = 
              io.grpc.MethodDescriptor.<io.sandstorm.exchange.api.msg.JobIdMsg, io.sandstorm.exchange.api.msg.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ControllerService", "OnJobCompleted"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.JobIdMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("OnJobCompleted"))
                  .build();
          }
        }
     }
     return getOnJobCompletedMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getOnJobFailedMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> METHOD_ON_JOB_FAILED = getOnJobFailedMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnJobFailedMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnJobFailedMethod() {
    return getOnJobFailedMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg,
      io.sandstorm.exchange.api.msg.ResultMsg> getOnJobFailedMethodHelper() {
    io.grpc.MethodDescriptor<io.sandstorm.exchange.api.msg.ResultNotifyMsg, io.sandstorm.exchange.api.msg.ResultMsg> getOnJobFailedMethod;
    if ((getOnJobFailedMethod = ControllerServiceGrpc.getOnJobFailedMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getOnJobFailedMethod = ControllerServiceGrpc.getOnJobFailedMethod) == null) {
          ControllerServiceGrpc.getOnJobFailedMethod = getOnJobFailedMethod = 
              io.grpc.MethodDescriptor.<io.sandstorm.exchange.api.msg.ResultNotifyMsg, io.sandstorm.exchange.api.msg.ResultMsg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ControllerService", "OnJobFailed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultNotifyMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.sandstorm.exchange.api.msg.ResultMsg.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("OnJobFailed"))
                  .build();
          }
        }
     }
     return getOnJobFailedMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ControllerServiceStub newStub(io.grpc.Channel channel) {
    return new ControllerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ControllerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ControllerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ControllerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ControllerServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ControllerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerLoadInjector(io.sandstorm.exchange.api.msg.LoadInjectorMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterLoadInjectorMethodHelper(), responseObserver);
    }

    /**
     */
    public void onPreparationSucceeded(io.sandstorm.exchange.api.msg.JobIdMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getOnPreparationSucceededMethodHelper(), responseObserver);
    }

    /**
     */
    public void onPreparationFailed(io.sandstorm.exchange.api.msg.ResultNotifyMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getOnPreparationFailedMethodHelper(), responseObserver);
    }

    /**
     */
    public void onJobCompleted(io.sandstorm.exchange.api.msg.JobIdMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getOnJobCompletedMethodHelper(), responseObserver);
    }

    /**
     */
    public void onJobFailed(io.sandstorm.exchange.api.msg.ResultNotifyMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnimplementedUnaryCall(getOnJobFailedMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterLoadInjectorMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.sandstorm.exchange.api.msg.LoadInjectorMsg,
                io.sandstorm.exchange.api.msg.ResultMsg>(
                  this, METHODID_REGISTER_LOAD_INJECTOR)))
          .addMethod(
            getOnPreparationSucceededMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.sandstorm.exchange.api.msg.JobIdMsg,
                io.sandstorm.exchange.api.msg.ResultMsg>(
                  this, METHODID_ON_PREPARATION_SUCCEEDED)))
          .addMethod(
            getOnPreparationFailedMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.sandstorm.exchange.api.msg.ResultNotifyMsg,
                io.sandstorm.exchange.api.msg.ResultMsg>(
                  this, METHODID_ON_PREPARATION_FAILED)))
          .addMethod(
            getOnJobCompletedMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.sandstorm.exchange.api.msg.JobIdMsg,
                io.sandstorm.exchange.api.msg.ResultMsg>(
                  this, METHODID_ON_JOB_COMPLETED)))
          .addMethod(
            getOnJobFailedMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.sandstorm.exchange.api.msg.ResultNotifyMsg,
                io.sandstorm.exchange.api.msg.ResultMsg>(
                  this, METHODID_ON_JOB_FAILED)))
          .build();
    }
  }

  /**
   */
  public static final class ControllerServiceStub extends io.grpc.stub.AbstractStub<ControllerServiceStub> {
    private ControllerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ControllerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControllerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ControllerServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerLoadInjector(io.sandstorm.exchange.api.msg.LoadInjectorMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterLoadInjectorMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onPreparationSucceeded(io.sandstorm.exchange.api.msg.JobIdMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOnPreparationSucceededMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onPreparationFailed(io.sandstorm.exchange.api.msg.ResultNotifyMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOnPreparationFailedMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onJobCompleted(io.sandstorm.exchange.api.msg.JobIdMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOnJobCompletedMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onJobFailed(io.sandstorm.exchange.api.msg.ResultNotifyMsg request,
        io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOnJobFailedMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ControllerServiceBlockingStub extends io.grpc.stub.AbstractStub<ControllerServiceBlockingStub> {
    private ControllerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ControllerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControllerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ControllerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.sandstorm.exchange.api.msg.ResultMsg registerLoadInjector(io.sandstorm.exchange.api.msg.LoadInjectorMsg request) {
      return blockingUnaryCall(
          getChannel(), getRegisterLoadInjectorMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public io.sandstorm.exchange.api.msg.ResultMsg onPreparationSucceeded(io.sandstorm.exchange.api.msg.JobIdMsg request) {
      return blockingUnaryCall(
          getChannel(), getOnPreparationSucceededMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public io.sandstorm.exchange.api.msg.ResultMsg onPreparationFailed(io.sandstorm.exchange.api.msg.ResultNotifyMsg request) {
      return blockingUnaryCall(
          getChannel(), getOnPreparationFailedMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public io.sandstorm.exchange.api.msg.ResultMsg onJobCompleted(io.sandstorm.exchange.api.msg.JobIdMsg request) {
      return blockingUnaryCall(
          getChannel(), getOnJobCompletedMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public io.sandstorm.exchange.api.msg.ResultMsg onJobFailed(io.sandstorm.exchange.api.msg.ResultNotifyMsg request) {
      return blockingUnaryCall(
          getChannel(), getOnJobFailedMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ControllerServiceFutureStub extends io.grpc.stub.AbstractStub<ControllerServiceFutureStub> {
    private ControllerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ControllerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControllerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ControllerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.sandstorm.exchange.api.msg.ResultMsg> registerLoadInjector(
        io.sandstorm.exchange.api.msg.LoadInjectorMsg request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterLoadInjectorMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.sandstorm.exchange.api.msg.ResultMsg> onPreparationSucceeded(
        io.sandstorm.exchange.api.msg.JobIdMsg request) {
      return futureUnaryCall(
          getChannel().newCall(getOnPreparationSucceededMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.sandstorm.exchange.api.msg.ResultMsg> onPreparationFailed(
        io.sandstorm.exchange.api.msg.ResultNotifyMsg request) {
      return futureUnaryCall(
          getChannel().newCall(getOnPreparationFailedMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.sandstorm.exchange.api.msg.ResultMsg> onJobCompleted(
        io.sandstorm.exchange.api.msg.JobIdMsg request) {
      return futureUnaryCall(
          getChannel().newCall(getOnJobCompletedMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.sandstorm.exchange.api.msg.ResultMsg> onJobFailed(
        io.sandstorm.exchange.api.msg.ResultNotifyMsg request) {
      return futureUnaryCall(
          getChannel().newCall(getOnJobFailedMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_LOAD_INJECTOR = 0;
  private static final int METHODID_ON_PREPARATION_SUCCEEDED = 1;
  private static final int METHODID_ON_PREPARATION_FAILED = 2;
  private static final int METHODID_ON_JOB_COMPLETED = 3;
  private static final int METHODID_ON_JOB_FAILED = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ControllerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ControllerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_LOAD_INJECTOR:
          serviceImpl.registerLoadInjector((io.sandstorm.exchange.api.msg.LoadInjectorMsg) request,
              (io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg>) responseObserver);
          break;
        case METHODID_ON_PREPARATION_SUCCEEDED:
          serviceImpl.onPreparationSucceeded((io.sandstorm.exchange.api.msg.JobIdMsg) request,
              (io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg>) responseObserver);
          break;
        case METHODID_ON_PREPARATION_FAILED:
          serviceImpl.onPreparationFailed((io.sandstorm.exchange.api.msg.ResultNotifyMsg) request,
              (io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg>) responseObserver);
          break;
        case METHODID_ON_JOB_COMPLETED:
          serviceImpl.onJobCompleted((io.sandstorm.exchange.api.msg.JobIdMsg) request,
              (io.grpc.stub.StreamObserver<io.sandstorm.exchange.api.msg.ResultMsg>) responseObserver);
          break;
        case METHODID_ON_JOB_FAILED:
          serviceImpl.onJobFailed((io.sandstorm.exchange.api.msg.ResultNotifyMsg) request,
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

  private static abstract class ControllerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ControllerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.sandstorm.exchange.api.ControllerServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ControllerService");
    }
  }

  private static final class ControllerServiceFileDescriptorSupplier
      extends ControllerServiceBaseDescriptorSupplier {
    ControllerServiceFileDescriptorSupplier() {}
  }

  private static final class ControllerServiceMethodDescriptorSupplier
      extends ControllerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ControllerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ControllerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ControllerServiceFileDescriptorSupplier())
              .addMethod(getRegisterLoadInjectorMethodHelper())
              .addMethod(getOnPreparationSucceededMethodHelper())
              .addMethod(getOnPreparationFailedMethodHelper())
              .addMethod(getOnJobCompletedMethodHelper())
              .addMethod(getOnJobFailedMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
