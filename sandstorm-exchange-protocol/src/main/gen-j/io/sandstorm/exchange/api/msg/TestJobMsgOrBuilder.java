// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package io.sandstorm.exchange.api.msg;

public interface TestJobMsgOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TestJobMsg)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string super_job_id = 2;</code>
   */
  java.lang.String getSuperJobId();
  /**
   * <code>string super_job_id = 2;</code>
   */
  com.google.protobuf.ByteString
      getSuperJobIdBytes();

  /**
   * <code>.TestScriptMsg script = 3;</code>
   */
  boolean hasScript();
  /**
   * <code>.TestScriptMsg script = 3;</code>
   */
  io.sandstorm.exchange.api.msg.TestScriptMsg getScript();
  /**
   * <code>.TestScriptMsg script = 3;</code>
   */
  io.sandstorm.exchange.api.msg.TestScriptMsgOrBuilder getScriptOrBuilder();

  /**
   * <code>string simulation_to_run = 4;</code>
   */
  java.lang.String getSimulationToRun();
  /**
   * <code>string simulation_to_run = 4;</code>
   */
  com.google.protobuf.ByteString
      getSimulationToRunBytes();

  /**
   * <code>.DataSetMsg data_set = 5;</code>
   */
  boolean hasDataSet();
  /**
   * <code>.DataSetMsg data_set = 5;</code>
   */
  io.sandstorm.exchange.api.msg.DataSetMsg getDataSet();
  /**
   * <code>.DataSetMsg data_set = 5;</code>
   */
  io.sandstorm.exchange.api.msg.DataSetMsgOrBuilder getDataSetOrBuilder();

  /**
   * <code>.LoadProfileMsg load_profile = 6;</code>
   */
  boolean hasLoadProfile();
  /**
   * <code>.LoadProfileMsg load_profile = 6;</code>
   */
  io.sandstorm.exchange.api.msg.LoadProfileMsg getLoadProfile();
  /**
   * <code>.LoadProfileMsg load_profile = 6;</code>
   */
  io.sandstorm.exchange.api.msg.LoadProfileMsgOrBuilder getLoadProfileOrBuilder();
}