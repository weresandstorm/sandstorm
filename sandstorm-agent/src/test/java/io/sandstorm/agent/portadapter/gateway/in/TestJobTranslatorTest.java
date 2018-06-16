package io.sandstorm.agent.portadapter.gateway.in;

import io.sandstorm.agent.domain.model.LocalTestJob;
import io.sandstorm.exchange.api.msg.DataChunkMsg;
import io.sandstorm.exchange.api.msg.DataSetMsg;
import io.sandstorm.exchange.api.msg.LoadProfileMsg;
import io.sandstorm.exchange.api.msg.RpsThrotStepMsg;
import io.sandstorm.exchange.api.msg.TestJobMsg;
import io.sandstorm.exchange.api.msg.TestScriptMsg;
import io.sandstorm.exchange.api.msg.UserInjectStepMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestJobTranslatorTest {

    @Test
    public void testToLocalTestJob() {

        TestScriptMsg testScriptMsg = TestScriptMsg.newBuilder()
            .setJarAlias("gatling-script.jar")
            .setJarUrl("http://qima-inc.com/")
            .build();

        DataSetMsg dataSetMsg = DataSetMsg.newBuilder()
            .setFeederFileName("basic-instant-kill.json")
            .addChunks(DataChunkMsg.newBuilder().setNo(0).setUrl("http://qima-inc.com/").setSignature("00000000"))
            .addChunks(DataChunkMsg.newBuilder().setNo(1).setUrl("http://qima-inc.com/").setSignature("00000000"))
            .addChunks(DataChunkMsg.newBuilder().setNo(2).setUrl("http://qima-inc.com/").setSignature("00000000"))
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
            .setSimulationToRun("io.sandstorm.gatling.script.simulation.BasicInstantKill")
            .setDataSet(dataSetMsg)
            .setScript(testScriptMsg)
            .setLoadProfile(loadProfileMsg)
            .build();

        LocalTestJob testJob = TestJobTranslator.toLocalTestJob(testJobMsg);
        System.out.println(testJob);
    }

}
