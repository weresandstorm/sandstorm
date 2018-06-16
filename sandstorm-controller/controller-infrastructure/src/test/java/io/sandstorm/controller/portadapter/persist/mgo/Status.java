package io.sandstorm.controller.portadapter.persist.mgo;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public final class Status {

    public static final Status preparing = new Status("preparing", 1);
    public static final Status prepared = new Status("prepared", 2);
    public static final Status running = new Status("running", 3);
    public static final Status completed = new Status("completed", 4);
    public static final Status failed = new Status("failed", 4);
    public static final Status stopping = new Status("stopping", 4);
    public static final Status stopped = new Status("stopped", 5);

    private String name;
    private int level;

    private Status() {}

    private Status(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String name() {
        return this.name;
    }

    public int level() {
        return this.level;
    }

    public static Status valueOf(String name) {
        switch (name) {
            case "preparing":
                return preparing;
            case "prepared":
                return prepared;
            case "running":
                return running;
            case "completed":
                return completed;
            case "failed":
                return failed;
            case "stopping":
                return stopping;
            case "stopped":
                return stopped;
            default:
                throw new IllegalArgumentException("No enum item with name: " + name);
        }
    }
}
