package io.sandstorm.common.query.domain;

import java.util.Optional;

/**
 * Query params containing only one optional param
 */
public class SimplePagingQParams extends Pageable {

    private Optional<String> valToMatch = Optional.empty();

    public void setValToMatch(String valToMatch) {
        this.valToMatch = Optional.ofNullable(valToMatch);
    }

    public Optional<String> valToMatch() {
        return valToMatch;
    }
}
