package io.sandstorm.agent.domain.model;

import io.sandstorm.agent.domain.AgentConfig;
import java.io.File;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LocalTestJob {

    private String id;
    private String superJobId;
    private String simulationToRun;
    private LocalTestScript testScript;
    private String feederFileName;
    private List<LocalDataChunk> dataChunks;
    private LocalLoadProfile loadProfile;

    private String feederFileDir;

    public String id() {
        return id;
    }

    public String superJobId() {
        return superJobId;
    }

    public String simulationToRun() {
        return simulationToRun;
    }

    public String feederFileName() {
        return feederFileName;
    }

    public String feederFileDir() {
        if (StringUtils.isBlank(feederFileName)) {
            return "";
        }
        if (null != feederFileDir) {
            return feederFileDir;
        }
        feederFileDir = AgentConfig.testDataRootDir() + File.separator + feederFileName.substring(0, feederFileName.lastIndexOf("."));
        return feederFileDir;
    }

    public LocalTestScript script() {
        return testScript;
    }

    public List<LocalDataChunk> dataChunks() {
        if (dataChunks == null) {
            return Collections.emptyList();
        }
        return this.dataChunks;
    }

    public LocalLoadProfile loadProfile() {
        return loadProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LocalTestJob))
            return false;

        LocalTestJob that = (LocalTestJob) o;

        return superJobId.equals(that.superJobId) && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return superJobId.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id").append(id)
            .append("superJobId").append(superJobId)
            .append("simulationToRun").append(simulationToRun)
            .append("testScript").append(testScript)
            .append("feederFileName").append(feederFileName)
            .toString();
    }
}
