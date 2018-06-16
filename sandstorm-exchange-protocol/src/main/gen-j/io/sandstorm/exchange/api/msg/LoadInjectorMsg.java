// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package io.sandstorm.exchange.api.msg;

/**
 * Protobuf type {@code LoadInjectorMsg}
 */
public  final class LoadInjectorMsg extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:LoadInjectorMsg)
    LoadInjectorMsgOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LoadInjectorMsg.newBuilder() to construct.
  private LoadInjectorMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LoadInjectorMsg() {
    hostname_ = "";
    port_ = 0;
    intranetIp_ = "";
    publicIp_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private LoadInjectorMsg(
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            hostname_ = s;
            break;
          }
          case 16: {

            port_ = input.readInt32();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            intranetIp_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            publicIp_ = s;
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
    return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_LoadInjectorMsg_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_LoadInjectorMsg_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.sandstorm.exchange.api.msg.LoadInjectorMsg.class, io.sandstorm.exchange.api.msg.LoadInjectorMsg.Builder.class);
  }

  public static final int HOSTNAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object hostname_;
  /**
   * <code>string hostname = 1;</code>
   */
  public java.lang.String getHostname() {
    java.lang.Object ref = hostname_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      hostname_ = s;
      return s;
    }
  }
  /**
   * <code>string hostname = 1;</code>
   */
  public com.google.protobuf.ByteString
      getHostnameBytes() {
    java.lang.Object ref = hostname_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      hostname_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PORT_FIELD_NUMBER = 2;
  private int port_;
  /**
   * <code>int32 port = 2;</code>
   */
  public int getPort() {
    return port_;
  }

  public static final int INTRANET_IP_FIELD_NUMBER = 3;
  private volatile java.lang.Object intranetIp_;
  /**
   * <code>string intranet_ip = 3;</code>
   */
  public java.lang.String getIntranetIp() {
    java.lang.Object ref = intranetIp_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      intranetIp_ = s;
      return s;
    }
  }
  /**
   * <code>string intranet_ip = 3;</code>
   */
  public com.google.protobuf.ByteString
      getIntranetIpBytes() {
    java.lang.Object ref = intranetIp_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      intranetIp_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PUBLIC_IP_FIELD_NUMBER = 4;
  private volatile java.lang.Object publicIp_;
  /**
   * <code>string public_ip = 4;</code>
   */
  public java.lang.String getPublicIp() {
    java.lang.Object ref = publicIp_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      publicIp_ = s;
      return s;
    }
  }
  /**
   * <code>string public_ip = 4;</code>
   */
  public com.google.protobuf.ByteString
      getPublicIpBytes() {
    java.lang.Object ref = publicIp_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      publicIp_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getHostnameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, hostname_);
    }
    if (port_ != 0) {
      output.writeInt32(2, port_);
    }
    if (!getIntranetIpBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, intranetIp_);
    }
    if (!getPublicIpBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, publicIp_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getHostnameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, hostname_);
    }
    if (port_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, port_);
    }
    if (!getIntranetIpBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, intranetIp_);
    }
    if (!getPublicIpBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, publicIp_);
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
    if (!(obj instanceof io.sandstorm.exchange.api.msg.LoadInjectorMsg)) {
      return super.equals(obj);
    }
    io.sandstorm.exchange.api.msg.LoadInjectorMsg other = (io.sandstorm.exchange.api.msg.LoadInjectorMsg) obj;

    boolean result = true;
    result = result && getHostname()
        .equals(other.getHostname());
    result = result && (getPort()
        == other.getPort());
    result = result && getIntranetIp()
        .equals(other.getIntranetIp());
    result = result && getPublicIp()
        .equals(other.getPublicIp());
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
    hash = (37 * hash) + HOSTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getHostname().hashCode();
    hash = (37 * hash) + PORT_FIELD_NUMBER;
    hash = (53 * hash) + getPort();
    hash = (37 * hash) + INTRANET_IP_FIELD_NUMBER;
    hash = (53 * hash) + getIntranetIp().hashCode();
    hash = (37 * hash) + PUBLIC_IP_FIELD_NUMBER;
    hash = (53 * hash) + getPublicIp().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg parseFrom(
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
  public static Builder newBuilder(io.sandstorm.exchange.api.msg.LoadInjectorMsg prototype) {
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
   * Protobuf type {@code LoadInjectorMsg}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:LoadInjectorMsg)
      io.sandstorm.exchange.api.msg.LoadInjectorMsgOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_LoadInjectorMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_LoadInjectorMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.sandstorm.exchange.api.msg.LoadInjectorMsg.class, io.sandstorm.exchange.api.msg.LoadInjectorMsg.Builder.class);
    }

    // Construct using io.sandstorm.exchange.api.msg.LoadInjectorMsg.newBuilder()
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
      hostname_ = "";

      port_ = 0;

      intranetIp_ = "";

      publicIp_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_LoadInjectorMsg_descriptor;
    }

    public io.sandstorm.exchange.api.msg.LoadInjectorMsg getDefaultInstanceForType() {
      return io.sandstorm.exchange.api.msg.LoadInjectorMsg.getDefaultInstance();
    }

    public io.sandstorm.exchange.api.msg.LoadInjectorMsg build() {
      io.sandstorm.exchange.api.msg.LoadInjectorMsg result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public io.sandstorm.exchange.api.msg.LoadInjectorMsg buildPartial() {
      io.sandstorm.exchange.api.msg.LoadInjectorMsg result = new io.sandstorm.exchange.api.msg.LoadInjectorMsg(this);
      result.hostname_ = hostname_;
      result.port_ = port_;
      result.intranetIp_ = intranetIp_;
      result.publicIp_ = publicIp_;
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
      if (other instanceof io.sandstorm.exchange.api.msg.LoadInjectorMsg) {
        return mergeFrom((io.sandstorm.exchange.api.msg.LoadInjectorMsg)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.sandstorm.exchange.api.msg.LoadInjectorMsg other) {
      if (other == io.sandstorm.exchange.api.msg.LoadInjectorMsg.getDefaultInstance()) return this;
      if (!other.getHostname().isEmpty()) {
        hostname_ = other.hostname_;
        onChanged();
      }
      if (other.getPort() != 0) {
        setPort(other.getPort());
      }
      if (!other.getIntranetIp().isEmpty()) {
        intranetIp_ = other.intranetIp_;
        onChanged();
      }
      if (!other.getPublicIp().isEmpty()) {
        publicIp_ = other.publicIp_;
        onChanged();
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
      io.sandstorm.exchange.api.msg.LoadInjectorMsg parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.sandstorm.exchange.api.msg.LoadInjectorMsg) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object hostname_ = "";
    /**
     * <code>string hostname = 1;</code>
     */
    public java.lang.String getHostname() {
      java.lang.Object ref = hostname_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        hostname_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string hostname = 1;</code>
     */
    public com.google.protobuf.ByteString
        getHostnameBytes() {
      java.lang.Object ref = hostname_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        hostname_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string hostname = 1;</code>
     */
    public Builder setHostname(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      hostname_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string hostname = 1;</code>
     */
    public Builder clearHostname() {
      
      hostname_ = getDefaultInstance().getHostname();
      onChanged();
      return this;
    }
    /**
     * <code>string hostname = 1;</code>
     */
    public Builder setHostnameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      hostname_ = value;
      onChanged();
      return this;
    }

    private int port_ ;
    /**
     * <code>int32 port = 2;</code>
     */
    public int getPort() {
      return port_;
    }
    /**
     * <code>int32 port = 2;</code>
     */
    public Builder setPort(int value) {
      
      port_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 port = 2;</code>
     */
    public Builder clearPort() {
      
      port_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object intranetIp_ = "";
    /**
     * <code>string intranet_ip = 3;</code>
     */
    public java.lang.String getIntranetIp() {
      java.lang.Object ref = intranetIp_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        intranetIp_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string intranet_ip = 3;</code>
     */
    public com.google.protobuf.ByteString
        getIntranetIpBytes() {
      java.lang.Object ref = intranetIp_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        intranetIp_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string intranet_ip = 3;</code>
     */
    public Builder setIntranetIp(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      intranetIp_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string intranet_ip = 3;</code>
     */
    public Builder clearIntranetIp() {
      
      intranetIp_ = getDefaultInstance().getIntranetIp();
      onChanged();
      return this;
    }
    /**
     * <code>string intranet_ip = 3;</code>
     */
    public Builder setIntranetIpBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      intranetIp_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object publicIp_ = "";
    /**
     * <code>string public_ip = 4;</code>
     */
    public java.lang.String getPublicIp() {
      java.lang.Object ref = publicIp_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        publicIp_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string public_ip = 4;</code>
     */
    public com.google.protobuf.ByteString
        getPublicIpBytes() {
      java.lang.Object ref = publicIp_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        publicIp_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string public_ip = 4;</code>
     */
    public Builder setPublicIp(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      publicIp_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string public_ip = 4;</code>
     */
    public Builder clearPublicIp() {
      
      publicIp_ = getDefaultInstance().getPublicIp();
      onChanged();
      return this;
    }
    /**
     * <code>string public_ip = 4;</code>
     */
    public Builder setPublicIpBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      publicIp_ = value;
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


    // @@protoc_insertion_point(builder_scope:LoadInjectorMsg)
  }

  // @@protoc_insertion_point(class_scope:LoadInjectorMsg)
  private static final io.sandstorm.exchange.api.msg.LoadInjectorMsg DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.sandstorm.exchange.api.msg.LoadInjectorMsg();
  }

  public static io.sandstorm.exchange.api.msg.LoadInjectorMsg getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<LoadInjectorMsg>
      PARSER = new com.google.protobuf.AbstractParser<LoadInjectorMsg>() {
    public LoadInjectorMsg parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new LoadInjectorMsg(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<LoadInjectorMsg> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LoadInjectorMsg> getParserForType() {
    return PARSER;
  }

  public io.sandstorm.exchange.api.msg.LoadInjectorMsg getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

