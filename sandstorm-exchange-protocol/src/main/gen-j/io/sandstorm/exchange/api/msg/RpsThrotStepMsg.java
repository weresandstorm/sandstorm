// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package io.sandstorm.exchange.api.msg;

/**
 * Protobuf type {@code RpsThrotStepMsg}
 */
public  final class RpsThrotStepMsg extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RpsThrotStepMsg)
    RpsThrotStepMsgOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RpsThrotStepMsg.newBuilder() to construct.
  private RpsThrotStepMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RpsThrotStepMsg() {
    rampTime_ = 0L;
    toRps_ = 0L;
    holdTime_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RpsThrotStepMsg(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            rampTime_ = input.readInt64();
            break;
          }
          case 16: {

            toRps_ = input.readInt64();
            break;
          }
          case 24: {

            holdTime_ = input.readInt64();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_RpsThrotStepMsg_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_RpsThrotStepMsg_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.sandstorm.exchange.api.msg.RpsThrotStepMsg.class, io.sandstorm.exchange.api.msg.RpsThrotStepMsg.Builder.class);
  }

  public static final int RAMP_TIME_FIELD_NUMBER = 1;
  private long rampTime_;
  /**
   * <code>int64 ramp_time = 1;</code>
   */
  public long getRampTime() {
    return rampTime_;
  }

  public static final int TO_RPS_FIELD_NUMBER = 2;
  private long toRps_;
  /**
   * <code>int64 to_rps = 2;</code>
   */
  public long getToRps() {
    return toRps_;
  }

  public static final int HOLD_TIME_FIELD_NUMBER = 3;
  private long holdTime_;
  /**
   * <code>int64 hold_time = 3;</code>
   */
  public long getHoldTime() {
    return holdTime_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (rampTime_ != 0L) {
      output.writeInt64(1, rampTime_);
    }
    if (toRps_ != 0L) {
      output.writeInt64(2, toRps_);
    }
    if (holdTime_ != 0L) {
      output.writeInt64(3, holdTime_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (rampTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, rampTime_);
    }
    if (toRps_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, toRps_);
    }
    if (holdTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, holdTime_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.sandstorm.exchange.api.msg.RpsThrotStepMsg)) {
      return super.equals(obj);
    }
    io.sandstorm.exchange.api.msg.RpsThrotStepMsg other = (io.sandstorm.exchange.api.msg.RpsThrotStepMsg) obj;

    boolean result = true;
    result = result && (getRampTime()
        == other.getRampTime());
    result = result && (getToRps()
        == other.getToRps());
    result = result && (getHoldTime()
        == other.getHoldTime());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RAMP_TIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRampTime());
    hash = (37 * hash) + TO_RPS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getToRps());
    hash = (37 * hash) + HOLD_TIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getHoldTime());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.sandstorm.exchange.api.msg.RpsThrotStepMsg prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code RpsThrotStepMsg}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RpsThrotStepMsg)
      io.sandstorm.exchange.api.msg.RpsThrotStepMsgOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_RpsThrotStepMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_RpsThrotStepMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.sandstorm.exchange.api.msg.RpsThrotStepMsg.class, io.sandstorm.exchange.api.msg.RpsThrotStepMsg.Builder.class);
    }

    // Construct using io.sandstorm.exchange.api.msg.RpsThrotStepMsg.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      rampTime_ = 0L;

      toRps_ = 0L;

      holdTime_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_RpsThrotStepMsg_descriptor;
    }

    public io.sandstorm.exchange.api.msg.RpsThrotStepMsg getDefaultInstanceForType() {
      return io.sandstorm.exchange.api.msg.RpsThrotStepMsg.getDefaultInstance();
    }

    public io.sandstorm.exchange.api.msg.RpsThrotStepMsg build() {
      io.sandstorm.exchange.api.msg.RpsThrotStepMsg result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public io.sandstorm.exchange.api.msg.RpsThrotStepMsg buildPartial() {
      io.sandstorm.exchange.api.msg.RpsThrotStepMsg result = new io.sandstorm.exchange.api.msg.RpsThrotStepMsg(this);
      result.rampTime_ = rampTime_;
      result.toRps_ = toRps_;
      result.holdTime_ = holdTime_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.sandstorm.exchange.api.msg.RpsThrotStepMsg) {
        return mergeFrom((io.sandstorm.exchange.api.msg.RpsThrotStepMsg)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.sandstorm.exchange.api.msg.RpsThrotStepMsg other) {
      if (other == io.sandstorm.exchange.api.msg.RpsThrotStepMsg.getDefaultInstance()) return this;
      if (other.getRampTime() != 0L) {
        setRampTime(other.getRampTime());
      }
      if (other.getToRps() != 0L) {
        setToRps(other.getToRps());
      }
      if (other.getHoldTime() != 0L) {
        setHoldTime(other.getHoldTime());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.sandstorm.exchange.api.msg.RpsThrotStepMsg parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.sandstorm.exchange.api.msg.RpsThrotStepMsg) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long rampTime_ ;
    /**
     * <code>int64 ramp_time = 1;</code>
     */
    public long getRampTime() {
      return rampTime_;
    }
    /**
     * <code>int64 ramp_time = 1;</code>
     */
    public Builder setRampTime(long value) {
      
      rampTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 ramp_time = 1;</code>
     */
    public Builder clearRampTime() {
      
      rampTime_ = 0L;
      onChanged();
      return this;
    }

    private long toRps_ ;
    /**
     * <code>int64 to_rps = 2;</code>
     */
    public long getToRps() {
      return toRps_;
    }
    /**
     * <code>int64 to_rps = 2;</code>
     */
    public Builder setToRps(long value) {
      
      toRps_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 to_rps = 2;</code>
     */
    public Builder clearToRps() {
      
      toRps_ = 0L;
      onChanged();
      return this;
    }

    private long holdTime_ ;
    /**
     * <code>int64 hold_time = 3;</code>
     */
    public long getHoldTime() {
      return holdTime_;
    }
    /**
     * <code>int64 hold_time = 3;</code>
     */
    public Builder setHoldTime(long value) {
      
      holdTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 hold_time = 3;</code>
     */
    public Builder clearHoldTime() {
      
      holdTime_ = 0L;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:RpsThrotStepMsg)
  }

  // @@protoc_insertion_point(class_scope:RpsThrotStepMsg)
  private static final io.sandstorm.exchange.api.msg.RpsThrotStepMsg DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.sandstorm.exchange.api.msg.RpsThrotStepMsg();
  }

  public static io.sandstorm.exchange.api.msg.RpsThrotStepMsg getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RpsThrotStepMsg>
      PARSER = new com.google.protobuf.AbstractParser<RpsThrotStepMsg>() {
    public RpsThrotStepMsg parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RpsThrotStepMsg(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RpsThrotStepMsg> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RpsThrotStepMsg> getParserForType() {
    return PARSER;
  }

  public io.sandstorm.exchange.api.msg.RpsThrotStepMsg getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

