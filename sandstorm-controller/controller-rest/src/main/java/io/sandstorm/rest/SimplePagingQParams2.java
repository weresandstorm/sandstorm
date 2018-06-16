package io.sandstorm.rest;

import io.sandstorm.common.query.domain.Pageable;

import java.util.Optional;

/**
 * Query params containing only one optional param
 */
public class SimplePagingQParams2<T> extends Pageable {

    private Optional<T> valToMatch = Optional.empty();

    public void setValToMatch(T valToMatch) {
        this.valToMatch = Optional.ofNullable(valToMatch);
    }

    public Optional<T> valToMatch() {
        return valToMatch;
    }
}

