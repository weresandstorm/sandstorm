package io.sandstorm.rest.validation;

import io.sandstorm.common.domain.model.EntityOrBuilder;

import static io.sandstorm.common.InputAssert.notBlank;

public abstract class BasicValidator implements EntityOrBuilder.Visitor {
    @Override
    public void v_creator(String creator) {
        notBlank(creator, "creator");
    }
}