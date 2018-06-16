package io.sandstorm.common.query.domain;

public class Existence {

    private boolean exist;

    public Existence(boolean exist) {
        this.exist = exist;
    }

    public boolean exist() {
        return this.exist;
    }

}
