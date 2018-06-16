package io.sandstorm.common.query.domain;

import java.util.List;

public class Page<T> {

    private long total;
    private List<T> items;

    private Page() {
    }

    public Page(long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public long total() {
        return this.total;
    }

    public List<T> items() {
        return this.items;
    }
}
