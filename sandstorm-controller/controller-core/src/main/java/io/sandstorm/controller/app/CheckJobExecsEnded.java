package io.sandstorm.controller.app;

public class CheckJobExecsEnded {

    private boolean allEnded;

    public CheckJobExecsEnded(boolean allEnded) {
        this.allEnded = allEnded;
    }

    public boolean allEnded() {
        return allEnded;
    }
}
