package io.sandstorm.agent.domain;

import io.sandstorm.agent.domain.model.LocalTestJob;
import io.sandstorm.agent.portadapter.gateway.in.TestJobTranslator;
import io.sandstorm.exchange.api.msg.DataChunkMsg;
import io.sandstorm.exchange.api.msg.DataSetMsg;
import io.sandstorm.exchange.api.msg.LoadProfileMsg;
import io.sandstorm.exchange.api.msg.RpsThrotStepMsg;
import io.sandstorm.exchange.api.msg.TestJobMsg;
import io.sandstorm.exchange.api.msg.TestScriptMsg;
import io.sandstorm.exchange.api.msg.UserInjectStepMsg;
import java.io.File;

public class AbstractTest {

    //If you want to test locally, please change the testDataRootDir value to $ProjectDir/target/test-classes
    protected final static String dataRootDir = AgentConfig.testDataRootDir();
    protected final static String feederFileDir = dataRootDir + File.separator + "basic-instant-kill";
    protected final static String feederFileName = "basic-instant-kill.json";

    protected LocalTestJob localTestJob() {
        TestScriptMsg testScriptMsg = TestScriptMsg.newBuilder()
            .setJarAlias("gatling-script.jar")
            .setJarUrl("/test-script/gatling-script.jar")
            .build();

        DataSetMsg dataSetMsg = DataSetMsg.newBuilder()
            .setFeederFileName(feederFileName)
            .addChunks(DataChunkMsg.newBuilder().setNo(3).setUrl("/test-data/InstantKill/instant-kill-1.json.gz").setSignature("7a126566b58eba1ba2b079aff1377cf8"))
            .addChunks(DataChunkMsg.newBuilder().setNo(2).setUrl("/test-data/InstantKill/instant-kill-2.json.gz").setSignature("7a126566b58eba1ba2b079aff1377cf8"))
            .addChunks(DataChunkMsg.newBuilder().setNo(1).setUrl("/test-data/InstantKill/instant-kill-3.json.gz").setSignature("7a126566b58eba1ba2b079aff1377cf8"))
            .build();

        LoadProfileMsg loadProfileMsg = LoadProfileMsg.newBuilder()
            .setScnRepeatTimes(60)
            .addUserInjectSteps(UserInjectStepMsg.newBuilder().setTotalUsers(10).setRateUps(2).setDuration(2))
            .addUserInjectSteps(UserInjectStepMsg.newBuilder().setTotalUsers(10).setRateUps(2).setDuration(2))
            .addRpsThrotSteps(RpsThrotStepMsg.newBuilder().setRampTime(60).setToRps(100).setHoldTime(120))
            .addRpsThrotSteps(RpsThrotStepMsg.newBuilder().setRampTime(60).setToRps(100).setHoldTime(120))
            .build();

        TestJobMsg testJobMsg = TestJobMsg.newBuilder()
            .setId("00000000")
            .setSuperJobId("00000000")
            .setSimulationToRun("io.sandstorm.gatling.script.test.BuyTickets")
            .setDataSet(dataSetMsg)
            .setScript(testScriptMsg)
            .setLoadProfile(loadProfileMsg)
            .build();

        LocalTestJob testJob = TestJobTranslator.toLocalTestJob(testJobMsg);
        return testJob;
    }
}
