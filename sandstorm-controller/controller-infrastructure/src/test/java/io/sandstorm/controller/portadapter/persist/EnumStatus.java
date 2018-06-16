package io.sandstorm.controller.portadapter.persist;

import java.util.ArrayList;
import java.util.List;

public enum EnumStatus {
    preparing,
    prepared,
    running,
    completed,
    failed,
    stopping,
    stopped;

    public static List<EnumStatus> endedStatuses() {
        List<EnumStatus> endedStatuses = new ArrayList<>(4);
        endedStatuses.add(completed);
        endedStatuses.add(failed);
        endedStatuses.add(stopping);
        endedStatuses.add(stopped);
        return endedStatuses;
    }
}
