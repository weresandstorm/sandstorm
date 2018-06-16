package io.sandstorm.agent.portadapter.gateway.in;

import io.sandstorm.agent.domain.model.LocalDataChunk;
import io.sandstorm.agent.domain.model.LocalLoadProfile;
import io.sandstorm.agent.domain.model.LocalLoadProfile.RpsThrotStep;
import io.sandstorm.agent.domain.model.LocalLoadProfile.UserInjectStep;
import io.sandstorm.agent.domain.model.LocalTestJob;
import io.sandstorm.agent.domain.model.LocalTestScript;
import io.sandstorm.exchange.api.msg.DataChunkMsg;
import io.sandstorm.exchange.api.msg.DataSetMsg;
import io.sandstorm.exchange.api.msg.LoadProfileMsg;
import io.sandstorm.exchange.api.msg.RpsThrotStepMsg;
import io.sandstorm.exchange.api.msg.TestJobMsg;
import io.sandstorm.exchange.api.msg.TestScriptMsg;
import io.sandstorm.exchange.api.msg.UserInjectStepMsg;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public final class TestJobTranslator {

    private static final String DATA_CHUNK_FILE_SUFFIX = ".json.gz";

    private TestJobTranslator() {
    }

    public static LocalTestJob toLocalTestJob(TestJobMsg source) {
        LocalTestJob target = new LocalTestJob();
        setField(target, "id", source.getId());
        setField(target, "superJobId", source.getSuperJobId());
        setField(target, "simulationToRun", source.getSimulationToRun());

        LocalTestScript testScript = toLocalTestScript(source.getScript());
        setField(target, "testScript", testScript);

        DataSetMsg dataSet = source.getDataSet();
        String feederFileName = dataSet.getFeederFileName();
        if (!StringUtils.isBlank(feederFileName)) {
            setField(target, "feederFileName", feederFileName);
            String basicChunkFileName = feederFileName.substring(0, feederFileName.lastIndexOf("."));
            List<LocalDataChunk> dataChunks = dataSet.getChunksList().stream()
                .map(item -> toLocalTestDataChunk(basicChunkFileName, item))
                .collect(Collectors.toList());
            setField(target, "dataChunks", dataChunks);
        }

        LocalLoadProfile loadProfile = toLocalLoadProfile(source.getLoadProfile());
        setField(target, "loadProfile", loadProfile);
        return target;
    }

    private static LocalTestScript toLocalTestScript(TestScriptMsg source) {
        LocalTestScript target = new LocalTestScript();
        setField(target, "jarAlias", source.getJarAlias());
        setField(target, "jarUrl", source.getJarUrl());
        return target;
    }

    private static LocalDataChunk toLocalTestDataChunk(String basicFileName, DataChunkMsg source) {
        LocalDataChunk target = new LocalDataChunk();
        setField(target, "no", source.getNo());
        setField(target, "name", basicFileName + "-" + source.getNo() + DATA_CHUNK_FILE_SUFFIX);
        setField(target, "url", source.getUrl());
        setField(target, "signature", source.getSignature());
        return target;
    }

    private static LocalLoadProfile toLocalLoadProfile(LoadProfileMsg source) {
        LocalLoadProfile target = new LocalLoadProfile();
        setField(target, "scnRepeatTimes", source.getScnRepeatTimes());
        List<UserInjectStep> userInjectSteps = source.getUserInjectStepsList().stream()
            .map(item -> toUserInjectStep(item))
            .collect(Collectors.toList());
        setField(target, "userInjectSteps", userInjectSteps);

        List<RpsThrotStep> rpsThrotSteps = source.getRpsThrotStepsList().stream()
            .map(item -> toRpsThrotStep(item))
            .collect(Collectors.toList());
        setField(target, "rpsThrotSteps", rpsThrotSteps);
        return target;
    }

    private static UserInjectStep toUserInjectStep(UserInjectStepMsg source) {
        UserInjectStep target = new UserInjectStep();
        setField(target, "totalUsers", source.getTotalUsers());
        setField(target, "rateUps", source.getRateUps());
        setField(target, "duration", source.getDuration());
        return target;
    }

    private static RpsThrotStep toRpsThrotStep(RpsThrotStepMsg source) {
        RpsThrotStep target = new RpsThrotStep();
        setField(target, "rampTime", source.getRampTime());
        setField(target, "toRps", source.getToRps());
        setField(target, "holdTime", source.getHoldTime());
        return target;
    }

    private static void setField(Object target, String fieldName, Object value) {
        try {
            FieldUtils.writeDeclaredField(target, fieldName, value, true);
        } catch (IllegalAccessException e) {
            throw new TranslationException(e);
        }
    }

}
