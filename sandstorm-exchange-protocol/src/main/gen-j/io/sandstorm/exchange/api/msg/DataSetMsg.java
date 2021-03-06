// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package io.sandstorm.exchange.api.msg;

/**
 * Protobuf type {@code DataSetMsg}
 */
public  final class DataSetMsg extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:DataSetMsg)
    DataSetMsgOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DataSetMsg.newBuilder() to construct.
  private DataSetMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DataSetMsg() {
    feederFileName_ = "";
    chunks_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DataSetMsg(
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

            feederFileName_ = s;
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              chunks_ = new java.util.ArrayList<io.sandstorm.exchange.api.msg.DataChunkMsg>();
              mutable_bitField0_ |= 0x00000002;
            }
            chunks_.add(
                input.readMessage(io.sandstorm.exchange.api.msg.DataChunkMsg.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        chunks_ = java.util.Collections.unmodifiableList(chunks_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_DataSetMsg_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_DataSetMsg_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.sandstorm.exchange.api.msg.DataSetMsg.class, io.sandstorm.exchange.api.msg.DataSetMsg.Builder.class);
  }

  private int bitField0_;
  public static final int FEEDER_FILE_NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object feederFileName_;
  /**
   * <code>string feeder_file_name = 1;</code>
   */
  public java.lang.String getFeederFileName() {
    java.lang.Object ref = feederFileName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      feederFileName_ = s;
      return s;
    }
  }
  /**
   * <code>string feeder_file_name = 1;</code>
   */
  public com.google.protobuf.ByteString
      getFeederFileNameBytes() {
    java.lang.Object ref = feederFileName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      feederFileName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CHUNKS_FIELD_NUMBER = 2;
  private java.util.List<io.sandstorm.exchange.api.msg.DataChunkMsg> chunks_;
  /**
   * <code>repeated .DataChunkMsg chunks = 2;</code>
   */
  public java.util.List<io.sandstorm.exchange.api.msg.DataChunkMsg> getChunksList() {
    return chunks_;
  }
  /**
   * <code>repeated .DataChunkMsg chunks = 2;</code>
   */
  public java.util.List<? extends io.sandstorm.exchange.api.msg.DataChunkMsgOrBuilder> 
      getChunksOrBuilderList() {
    return chunks_;
  }
  /**
   * <code>repeated .DataChunkMsg chunks = 2;</code>
   */
  public int getChunksCount() {
    return chunks_.size();
  }
  /**
   * <code>repeated .DataChunkMsg chunks = 2;</code>
   */
  public io.sandstorm.exchange.api.msg.DataChunkMsg getChunks(int index) {
    return chunks_.get(index);
  }
  /**
   * <code>repeated .DataChunkMsg chunks = 2;</code>
   */
  public io.sandstorm.exchange.api.msg.DataChunkMsgOrBuilder getChunksOrBuilder(
      int index) {
    return chunks_.get(index);
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
    if (!getFeederFileNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, feederFileName_);
    }
    for (int i = 0; i < chunks_.size(); i++) {
      output.writeMessage(2, chunks_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getFeederFileNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, feederFileName_);
    }
    for (int i = 0; i < chunks_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, chunks_.get(i));
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
    if (!(obj instanceof io.sandstorm.exchange.api.msg.DataSetMsg)) {
      return super.equals(obj);
    }
    io.sandstorm.exchange.api.msg.DataSetMsg other = (io.sandstorm.exchange.api.msg.DataSetMsg) obj;

    boolean result = true;
    result = result && getFeederFileName()
        .equals(other.getFeederFileName());
    result = result && getChunksList()
        .equals(other.getChunksList());
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
    hash = (37 * hash) + FEEDER_FILE_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getFeederFileName().hashCode();
    if (getChunksCount() > 0) {
      hash = (37 * hash) + CHUNKS_FIELD_NUMBER;
      hash = (53 * hash) + getChunksList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.sandstorm.exchange.api.msg.DataSetMsg parseFrom(
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
  public static Builder newBuilder(io.sandstorm.exchange.api.msg.DataSetMsg prototype) {
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
   * Protobuf type {@code DataSetMsg}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:DataSetMsg)
      io.sandstorm.exchange.api.msg.DataSetMsgOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_DataSetMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_DataSetMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.sandstorm.exchange.api.msg.DataSetMsg.class, io.sandstorm.exchange.api.msg.DataSetMsg.Builder.class);
    }

    // Construct using io.sandstorm.exchange.api.msg.DataSetMsg.newBuilder()
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
        getChunksFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      feederFileName_ = "";

      if (chunksBuilder_ == null) {
        chunks_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        chunksBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.sandstorm.exchange.api.msg.TestTaskProto.internal_static_DataSetMsg_descriptor;
    }

    public io.sandstorm.exchange.api.msg.DataSetMsg getDefaultInstanceForType() {
      return io.sandstorm.exchange.api.msg.DataSetMsg.getDefaultInstance();
    }

    public io.sandstorm.exchange.api.msg.DataSetMsg build() {
      io.sandstorm.exchange.api.msg.DataSetMsg result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public io.sandstorm.exchange.api.msg.DataSetMsg buildPartial() {
      io.sandstorm.exchange.api.msg.DataSetMsg result = new io.sandstorm.exchange.api.msg.DataSetMsg(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.feederFileName_ = feederFileName_;
      if (chunksBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          chunks_ = java.util.Collections.unmodifiableList(chunks_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.chunks_ = chunks_;
      } else {
        result.chunks_ = chunksBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof io.sandstorm.exchange.api.msg.DataSetMsg) {
        return mergeFrom((io.sandstorm.exchange.api.msg.DataSetMsg)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.sandstorm.exchange.api.msg.DataSetMsg other) {
      if (other == io.sandstorm.exchange.api.msg.DataSetMsg.getDefaultInstance()) return this;
      if (!other.getFeederFileName().isEmpty()) {
        feederFileName_ = other.feederFileName_;
        onChanged();
      }
      if (chunksBuilder_ == null) {
        if (!other.chunks_.isEmpty()) {
          if (chunks_.isEmpty()) {
            chunks_ = other.chunks_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureChunksIsMutable();
            chunks_.addAll(other.chunks_);
          }
          onChanged();
        }
      } else {
        if (!other.chunks_.isEmpty()) {
          if (chunksBuilder_.isEmpty()) {
            chunksBuilder_.dispose();
            chunksBuilder_ = null;
            chunks_ = other.chunks_;
            bitField0_ = (bitField0_ & ~0x00000002);
            chunksBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getChunksFieldBuilder() : null;
          } else {
            chunksBuilder_.addAllMessages(other.chunks_);
          }
        }
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
      io.sandstorm.exchange.api.msg.DataSetMsg parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.sandstorm.exchange.api.msg.DataSetMsg) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object feederFileName_ = "";
    /**
     * <code>string feeder_file_name = 1;</code>
     */
    public java.lang.String getFeederFileName() {
      java.lang.Object ref = feederFileName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        feederFileName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string feeder_file_name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getFeederFileNameBytes() {
      java.lang.Object ref = feederFileName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        feederFileName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string feeder_file_name = 1;</code>
     */
    public Builder setFeederFileName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      feederFileName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string feeder_file_name = 1;</code>
     */
    public Builder clearFeederFileName() {
      
      feederFileName_ = getDefaultInstance().getFeederFileName();
      onChanged();
      return this;
    }
    /**
     * <code>string feeder_file_name = 1;</code>
     */
    public Builder setFeederFileNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      feederFileName_ = value;
      onChanged();
      return this;
    }

    private java.util.List<io.sandstorm.exchange.api.msg.DataChunkMsg> chunks_ =
      java.util.Collections.emptyList();
    private void ensureChunksIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        chunks_ = new java.util.ArrayList<io.sandstorm.exchange.api.msg.DataChunkMsg>(chunks_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        io.sandstorm.exchange.api.msg.DataChunkMsg, io.sandstorm.exchange.api.msg.DataChunkMsg.Builder, io.sandstorm.exchange.api.msg.DataChunkMsgOrBuilder> chunksBuilder_;

    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public java.util.List<io.sandstorm.exchange.api.msg.DataChunkMsg> getChunksList() {
      if (chunksBuilder_ == null) {
        return java.util.Collections.unmodifiableList(chunks_);
      } else {
        return chunksBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public int getChunksCount() {
      if (chunksBuilder_ == null) {
        return chunks_.size();
      } else {
        return chunksBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public io.sandstorm.exchange.api.msg.DataChunkMsg getChunks(int index) {
      if (chunksBuilder_ == null) {
        return chunks_.get(index);
      } else {
        return chunksBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public Builder setChunks(
        int index, io.sandstorm.exchange.api.msg.DataChunkMsg value) {
      if (chunksBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureChunksIsMutable();
        chunks_.set(index, value);
        onChanged();
      } else {
        chunksBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public Builder setChunks(
        int index, io.sandstorm.exchange.api.msg.DataChunkMsg.Builder builderForValue) {
      if (chunksBuilder_ == null) {
        ensureChunksIsMutable();
        chunks_.set(index, builderForValue.build());
        onChanged();
      } else {
        chunksBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public Builder addChunks(io.sandstorm.exchange.api.msg.DataChunkMsg value) {
      if (chunksBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureChunksIsMutable();
        chunks_.add(value);
        onChanged();
      } else {
        chunksBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public Builder addChunks(
        int index, io.sandstorm.exchange.api.msg.DataChunkMsg value) {
      if (chunksBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureChunksIsMutable();
        chunks_.add(index, value);
        onChanged();
      } else {
        chunksBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public Builder addChunks(
        io.sandstorm.exchange.api.msg.DataChunkMsg.Builder builderForValue) {
      if (chunksBuilder_ == null) {
        ensureChunksIsMutable();
        chunks_.add(builderForValue.build());
        onChanged();
      } else {
        chunksBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public Builder addChunks(
        int index, io.sandstorm.exchange.api.msg.DataChunkMsg.Builder builderForValue) {
      if (chunksBuilder_ == null) {
        ensureChunksIsMutable();
        chunks_.add(index, builderForValue.build());
        onChanged();
      } else {
        chunksBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public Builder addAllChunks(
        java.lang.Iterable<? extends io.sandstorm.exchange.api.msg.DataChunkMsg> values) {
      if (chunksBuilder_ == null) {
        ensureChunksIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, chunks_);
        onChanged();
      } else {
        chunksBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public Builder clearChunks() {
      if (chunksBuilder_ == null) {
        chunks_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        chunksBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public Builder removeChunks(int index) {
      if (chunksBuilder_ == null) {
        ensureChunksIsMutable();
        chunks_.remove(index);
        onChanged();
      } else {
        chunksBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public io.sandstorm.exchange.api.msg.DataChunkMsg.Builder getChunksBuilder(
        int index) {
      return getChunksFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public io.sandstorm.exchange.api.msg.DataChunkMsgOrBuilder getChunksOrBuilder(
        int index) {
      if (chunksBuilder_ == null) {
        return chunks_.get(index);  } else {
        return chunksBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public java.util.List<? extends io.sandstorm.exchange.api.msg.DataChunkMsgOrBuilder> 
         getChunksOrBuilderList() {
      if (chunksBuilder_ != null) {
        return chunksBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(chunks_);
      }
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public io.sandstorm.exchange.api.msg.DataChunkMsg.Builder addChunksBuilder() {
      return getChunksFieldBuilder().addBuilder(
          io.sandstorm.exchange.api.msg.DataChunkMsg.getDefaultInstance());
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public io.sandstorm.exchange.api.msg.DataChunkMsg.Builder addChunksBuilder(
        int index) {
      return getChunksFieldBuilder().addBuilder(
          index, io.sandstorm.exchange.api.msg.DataChunkMsg.getDefaultInstance());
    }
    /**
     * <code>repeated .DataChunkMsg chunks = 2;</code>
     */
    public java.util.List<io.sandstorm.exchange.api.msg.DataChunkMsg.Builder> 
         getChunksBuilderList() {
      return getChunksFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        io.sandstorm.exchange.api.msg.DataChunkMsg, io.sandstorm.exchange.api.msg.DataChunkMsg.Builder, io.sandstorm.exchange.api.msg.DataChunkMsgOrBuilder> 
        getChunksFieldBuilder() {
      if (chunksBuilder_ == null) {
        chunksBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            io.sandstorm.exchange.api.msg.DataChunkMsg, io.sandstorm.exchange.api.msg.DataChunkMsg.Builder, io.sandstorm.exchange.api.msg.DataChunkMsgOrBuilder>(
                chunks_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        chunks_ = null;
      }
      return chunksBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:DataSetMsg)
  }

  // @@protoc_insertion_point(class_scope:DataSetMsg)
  private static final io.sandstorm.exchange.api.msg.DataSetMsg DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.sandstorm.exchange.api.msg.DataSetMsg();
  }

  public static io.sandstorm.exchange.api.msg.DataSetMsg getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DataSetMsg>
      PARSER = new com.google.protobuf.AbstractParser<DataSetMsg>() {
    public DataSetMsg parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DataSetMsg(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DataSetMsg> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DataSetMsg> getParserForType() {
    return PARSER;
  }

  public io.sandstorm.exchange.api.msg.DataSetMsg getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

