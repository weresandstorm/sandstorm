package io.sandstorm.spi.storage;

import io.sandstorm.spi.util.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

public interface DataSetStorage {

    /**
     * Puts the data set from the location denoted by {@code sourceUrl} to somewhere, or keeps unchanged.
     * <p>The return result are urls of the chunks which compose the data set. If the data set isn't split, you can
     * think that it has only one chunk.
     *
     * @param dataSetName name of the data set denoted by {@code sourceUrl}
     * @param sourceUrl   the original location of the data set
     * @param revision    the revision number of the data set, and it starts with 0
     * @return the chunks composing the data set
     */
    List<Chunk> put(String dataSetName, String sourceUrl, int revision);

    /**
     * Puts the data set represented by {@code chunkStreams} to somewhere.
     * <p>The return result are urls of the chunks which compose the data set. If the data set isn't split, you can
     * think that it has only one chunk.
     *
     * @param dataSetName  name of the data set denoted by {@code sourceUrl}
     * @param chunkStreams the {@link InputStream}s of the chunks
     * @param revision     the revision number of the data set, and it starts with 0
     * @return the chunks composing the data set
     */
    List<Chunk> put(String dataSetName, List<InputStream> chunkStreams, int revision);

    /**
     * Obtains the data chunk from location denoted by {@code chunkUrl}
     *
     * @param chunkUrl The location of current data chunk
     * @param action   when the stream of jar file is obtained, then the action to be performed
     * @return {@code chunkUrl}
     */
    String get(String chunkUrl, Consumer<InputStream> action);

    final class Chunk {

        /**
         * The number of current chunk. <p>Used for situations where the data records in chunks are in order across all
         * chunks
         */
        private final int no;

        /**
         * The location of current data chunk
         */
        private final String url;

        /**
         * The checksum for current data chunk file
         */
        private final String signature;

        /**
         * The no field of the created object defaults to 0.
         */
        public Chunk(String url, String signature) {
            this(0, url, signature);
        }

        public Chunk(int no, String url, String signature) {
            if (StringUtils.isBlank(url)) {
                throw new IllegalArgumentException("url is blank");
            }
            if (StringUtils.isBlank(signature)) {
                throw new IllegalArgumentException("signature is blank");
            }
            this.no = no;
            this.url = url;
            this.signature = signature;
        }

        public int no() {
            return this.no;
        }

        public String url() {
            return this.url;
        }

        public String signature() {
            return this.signature;
        }
    }

}
