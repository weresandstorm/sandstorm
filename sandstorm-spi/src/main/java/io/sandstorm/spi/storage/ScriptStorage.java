package io.sandstorm.spi.storage;

import java.io.InputStream;
import java.util.function.Consumer;

public interface ScriptStorage {

    /**
     * Stores given jar file somewhere.
     *
     * @param jarName the alias given by the creator of the test-script entity
     * @param jarContent the content of the jar file
     * @param revision the version of the jar content
     * @return a url string that indicates the location where given jar file are stored
     */
    String put(String jarName, byte[] jarContent, int revision);

    /**
     * Obtains the content of the jar file from somewhere.
     *
     * @param jarUrl a url string that denotes the location where given jar file are stored
     * @param action when the stream of jar file is obtained, then the action to be performed
     * @return {@code jarUrl}
     */
    String get(String jarUrl, Consumer<InputStream> action);

}
