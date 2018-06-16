package io.sandstorm.agent.domain.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LocalDataChunk {

    private int no;
    private String name;
    private String url;
    private String signature;

    public LocalDataChunk() {
    }

    public int no() {
        return no;
    }

    public String name() {
        return name;
    }

    public String url() {
        return url;
    }

    public String signature() {
        return signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LocalDataChunk))
            return false;

        LocalDataChunk that = (LocalDataChunk) o;

        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("no").append(no)
            .append("name").append(name)
            .append("url").append(url)
            .append("signature").append(signature)
            .toString();
    }
}
