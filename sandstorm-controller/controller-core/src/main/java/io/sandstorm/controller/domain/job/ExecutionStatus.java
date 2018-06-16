package io.sandstorm.controller.domain.job;

import com.google.common.collect.Lists;

import java.util.List;

public enum ExecutionStatus {

    preparing,
    prepared,
    running,
    completed,
    failed,
    stopping,
    stopped;

    public static List<ExecutionStatus> endedStatuses() {
        return Lists.newArrayList(completed, failed, stopping, stopped);
    }

}
