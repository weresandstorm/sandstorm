// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package io.sandstorm.exchange.api.msg;

public interface DataChunkMsgOrBuilder extends
    // @@protoc_insertion_point(interface_extends:DataChunkMsg)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 no = 1;</code>
   */
  int getNo();

  /**
   * <code>string url = 2;</code>
   */
  java.lang.String getUrl();
  /**
   * <code>string url = 2;</code>
   */
  com.google.protobuf.ByteString
      getUrlBytes();

  /**
   * <code>string signature = 3;</code>
   */
  java.lang.String getSignature();
  /**
   * <code>string signature = 3;</code>
   */
  com.google.protobuf.ByteString
      getSignatureBytes();
}