package io.sandstorm.controller.portadapter.gateway.out;

import io.sandstorm.controller.domain.job.*;
import io.sandstorm.controller.domain.resource.DataChunk;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.LoadInjector;
import io.sandstorm.controller.domain.resource.TestScript;
import io.sandstorm.exchange.api.msg.*;
import io.sandstorm.exchange.helper.ServerAddress;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class AdaptingAgentService implements AgentService {

    private static final Logger logger = LoggerFactory.getLogger(AdaptingAgentService.class);

    private final Map<ServerAddress, AgentClient> agentClients = new ConcurrentHashMap<>(30);

    @Override
    public void prepareFor(JobSliceExecution sliceExec) {
        logger.info("Before request for preparing for JobSliceExecution [id:{}]", sliceExec.id());
        getAgentClient(sliceExec.injector()).prepareFor(translate(sliceExec));
    }

    @Override
    public void startTestEngine(JobSliceExecution sliceExec) {
        logger.info("Before request for starting engine for JobSliceExecution [id:{}]", sliceExec.id());
        getAgentClient(sliceExec.injector()).startTestEngine(translate(sliceExec));
    }

    @Override
    public void stop(JobSliceExecution sliceExec) {
        logger.info("Before request for stopping JobSliceExecution [id:{}]", sliceExec.id());
        getAgentClient(sliceExec.injector()).stop(translate(sliceExec));
    }

    @PreDestroy
    public void releaseResources() {
        agentClients.values().forEach(client -> client.shutdown());
    }

    private AgentClient getAgentClient(final LoadInjector injector) {
        ServerAddress key = new ServerAddress(
                injector.intranetIp(),
                injector.port());
        return agentClients.computeIfAbsent(key, serverAddress -> new AgentClient(serverAddress));
    }

    private TestJobMsg translate(JobSliceExecution source) {
        TestJobMsg.Builder builder = TestJobMsg.newBuilder()
                .setId(source.idAsString())
                .setSuperJobId(source.jobExecId().toHexString())
                .setScript(translate((TestScript) readField("script", source)))
                .setSimulationToRun(readField("simulationToRun", source))
                .setLoadProfile(translate((LoadProfile.Slice) readField("loadProfile", source)));

        DataSet.Slice dataSet = readField("dataSet", source);
        if (dataSet != null) {
            builder.setDataSet(translate(dataSet));
        }

        return builder.build();
    }

    private TestScriptMsg translate(TestScript testScript) {
        return TestScriptMsg.newBuilder()
                .setJarAlias(readField("jarAlias", testScript))
                .setJarUrl(readField("jarUrl", testScript))
                .build();
    }

    private DataSetMsg translate(DataSet.Slice source) {
        List<DataChunk.Traits> chunks =
                readField("chunks", source);
        return DataSetMsg.newBuilder()
                .setFeederFileName(readField("feederFileName", source))
                .addAllChunks(
                        chunks.stream()
                                .map(item -> translate(item))
                                .collect(Collectors.toList()))
                .build();
    }

    private DataChunkMsg translate(DataChunk.Traits source) {
        return DataChunkMsg.newBuilder()
                .setNo(readField("no", source))
                .setUrl(readField("url", source))
                .setSignature(readField("signature", source))
                .build();
    }

    private LoadProfileMsg translate(LoadProfile.Slice source) {
        List<UserInjectStep> userInjectSteps =
                readField("userInjectSteps", source);
        List<RpsThrotStep> rpsThrotSteps =
                readField("rpsThrotSteps", source);
        LoadProfileMsg.Builder builder = LoadProfileMsg.newBuilder()
                .setScnRepeatTimes(readField("scnRepeatTimes", source))
                .addAllUserInjectSteps(
                        userInjectSteps.stream()
                                .map(item -> translate(item))
                                .collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(rpsThrotSteps)) {
            builder.addAllRpsThrotSteps(
                    rpsThrotSteps.stream()
                            .map(item -> translate(item))
                            .collect(Collectors.toList()));
        }
        return builder.build();
    }

    private UserInjectStepMsg translate(UserInjectStep source) {
        return UserInjectStepMsg.newBuilder()
                .setTotalUsers(readField("totalUsers", source))
                .setRateUps(readField("rateUps", source))
                .setDuration(readField("duration", source))
                .build();
    }

    private RpsThrotStepMsg translate(RpsThrotStep source) {
        return RpsThrotStepMsg.newBuilder()
                .setRampTime(readField("rampTime", source))
                .setToRps(readField("toRps", source))
                .setHoldTime(readField("holdTime", source))
                .build();
    }

    private <T> T readField(String fieldName, Object target) {
        try {
            return (T) FieldUtils.readField(target, fieldName, true);
        } catch (IllegalAccessException e) {
            throw new TranslationException(e);
        }
    }
}
