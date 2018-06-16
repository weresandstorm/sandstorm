package io.sandstorm.controller.app;

import io.sandstorm.common.InputAssert;

public final class TempFile {
    private String path;

    private TempFile() {
    }

    TempFile(String path) {
        this.path = path;
    }

    public String path() {
        return this.path;
    }

    public void validate() {
        InputAssert.notBlank("path", path);
    }

}
