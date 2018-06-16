package io.sandstorm.spi.impl;


import io.sandstorm.spi.storage.ScriptStorage;

import java.io.InputStream;
import java.util.function.Consumer;

public class DefaultScriptStorage implements ScriptStorage {

    @Override
    public String put(String jarName, byte[] jarContent, int revision) {
        return "";
    }

    @Override
    public String get(String jarUrl, Consumer<InputStream> action) {
        return "";
    }

}
