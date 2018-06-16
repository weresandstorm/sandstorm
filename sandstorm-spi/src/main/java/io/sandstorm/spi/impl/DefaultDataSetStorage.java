package io.sandstorm.spi.impl;

import io.sandstorm.spi.storage.DataSetStorage;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DefaultDataSetStorage implements DataSetStorage {

    @Override
    public List<Chunk> put(String dataSetName, String sourceUrl, int revision) {
        return Collections.emptyList();
    }

    @Override
    public List<Chunk> put(String dataSetName, List<InputStream> chunkStreams, int revision) {
        return Collections.emptyList();
    }

    @Override
    public String get(String chunkUrl, Consumer<InputStream> action) {
        return "";
    }

}
