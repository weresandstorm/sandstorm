package io.sandstorm.agent.domain.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LocalTestScript {

    private String jarAlias;
    private String jarUrl;

    public String jarAlias() {
        return jarAlias;
    }

    public String jarUrl() {
        return jarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LocalTestScript))
            return false;

        LocalTestScript that = (LocalTestScript) o;

        return jarUrl.equals(that.jarUrl);
    }

    @Override
    public int hashCode() {
        return jarUrl.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("jarAlias").append(jarAlias)
            .append("jarUrl").append(jarUrl)
            .toString();
    }
}
