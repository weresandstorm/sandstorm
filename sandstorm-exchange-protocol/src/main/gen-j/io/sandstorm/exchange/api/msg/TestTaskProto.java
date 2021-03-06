// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

package io.sandstorm.exchange.api.msg;

public final class TestTaskProto {
  private TestTaskProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ResultMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResultMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ResultNotifyMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResultNotifyMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_VoidMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_VoidMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LoadInjectorMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LoadInjectorMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TestScriptMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TestScriptMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DataSetMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DataSetMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DataChunkMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DataChunkMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TestJobMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TestJobMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LoadProfileMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LoadProfileMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserInjectStepMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserInjectStepMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RpsThrotStepMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RpsThrotStepMsg_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_JobIdMsg_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_JobIdMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016messages.proto\"*\n\tResultMsg\022\014\n\004code\030\001 " +
      "\001(\005\022\017\n\007message\030\002 \001(\t\"9\n\017ResultNotifyMsg\022" +
      "\n\n\002id\030\001 \001(\t\022\032\n\006result\030\002 \001(\0132\n.ResultMsg\"" +
      "\t\n\007VoidMsg\"Y\n\017LoadInjectorMsg\022\020\n\010hostnam" +
      "e\030\001 \001(\t\022\014\n\004port\030\002 \001(\005\022\023\n\013intranet_ip\030\003 \001" +
      "(\t\022\021\n\tpublic_ip\030\004 \001(\t\"3\n\rTestScriptMsg\022\021" +
      "\n\tjar_alias\030\001 \001(\t\022\017\n\007jar_url\030\002 \001(\t\"E\n\nDa" +
      "taSetMsg\022\030\n\020feeder_file_name\030\001 \001(\t\022\035\n\006ch" +
      "unks\030\002 \003(\0132\r.DataChunkMsg\":\n\014DataChunkMs" +
      "g\022\n\n\002no\030\001 \001(\005\022\013\n\003url\030\002 \001(\t\022\021\n\tsignature\030" +
      "\003 \001(\t\"\257\001\n\nTestJobMsg\022\n\n\002id\030\001 \001(\t\022\024\n\014supe" +
      "r_job_id\030\002 \001(\t\022\036\n\006script\030\003 \001(\0132\016.TestScr" +
      "iptMsg\022\031\n\021simulation_to_run\030\004 \001(\t\022\035\n\010dat" +
      "a_set\030\005 \001(\0132\013.DataSetMsg\022%\n\014load_profile" +
      "\030\006 \001(\0132\017.LoadProfileMsg\"\204\001\n\016LoadProfileM" +
      "sg\022\030\n\020scn_repeat_times\030\001 \001(\003\022-\n\021user_inj" +
      "ect_steps\030\002 \003(\0132\022.UserInjectStepMsg\022)\n\017r" +
      "ps_throt_steps\030\003 \003(\0132\020.RpsThrotStepMsg\"L" +
      "\n\021UserInjectStepMsg\022\023\n\013total_users\030\001 \001(\003" +
      "\022\020\n\010rate_ups\030\002 \001(\003\022\020\n\010duration\030\003 \001(\003\"G\n\017" +
      "RpsThrotStepMsg\022\021\n\tramp_time\030\001 \001(\003\022\016\n\006to" +
      "_rps\030\002 \001(\003\022\021\n\thold_time\030\003 \001(\003\"\026\n\010JobIdMs" +
      "g\022\n\n\002id\030\001 \001(\tB0\n\035io.sandstorm.exchange.a" +
      "pi.msgB\rTestTaskProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_ResultMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ResultMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ResultMsg_descriptor,
        new java.lang.String[] { "Code", "Message", });
    internal_static_ResultNotifyMsg_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ResultNotifyMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ResultNotifyMsg_descriptor,
        new java.lang.String[] { "Id", "Result", });
    internal_static_VoidMsg_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_VoidMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_VoidMsg_descriptor,
        new java.lang.String[] { });
    internal_static_LoadInjectorMsg_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_LoadInjectorMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LoadInjectorMsg_descriptor,
        new java.lang.String[] { "Hostname", "Port", "IntranetIp", "PublicIp", });
    internal_static_TestScriptMsg_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_TestScriptMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TestScriptMsg_descriptor,
        new java.lang.String[] { "JarAlias", "JarUrl", });
    internal_static_DataSetMsg_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_DataSetMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DataSetMsg_descriptor,
        new java.lang.String[] { "FeederFileName", "Chunks", });
    internal_static_DataChunkMsg_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_DataChunkMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DataChunkMsg_descriptor,
        new java.lang.String[] { "No", "Url", "Signature", });
    internal_static_TestJobMsg_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_TestJobMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TestJobMsg_descriptor,
        new java.lang.String[] { "Id", "SuperJobId", "Script", "SimulationToRun", "DataSet", "LoadProfile", });
    internal_static_LoadProfileMsg_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_LoadProfileMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LoadProfileMsg_descriptor,
        new java.lang.String[] { "ScnRepeatTimes", "UserInjectSteps", "RpsThrotSteps", });
    internal_static_UserInjectStepMsg_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_UserInjectStepMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserInjectStepMsg_descriptor,
        new java.lang.String[] { "TotalUsers", "RateUps", "Duration", });
    internal_static_RpsThrotStepMsg_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_RpsThrotStepMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RpsThrotStepMsg_descriptor,
        new java.lang.String[] { "RampTime", "ToRps", "HoldTime", });
    internal_static_JobIdMsg_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_JobIdMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_JobIdMsg_descriptor,
        new java.lang.String[] { "Id", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
