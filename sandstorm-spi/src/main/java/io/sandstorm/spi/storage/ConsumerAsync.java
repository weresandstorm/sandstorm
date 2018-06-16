package io.sandstorm.spi.storage;

import java.util.concurrent.CompletableFuture;

/**
 * Represents an async operation that accepts a type of T argument and a completedFuture {@link CompletableFuture}
 * argument, and returns no result. so it can async execution. {@code ConsumerAsync} is async expected to operate via
 * side-effects.
 */
@FunctionalInterface
public interface ConsumerAsync<T, U> {

    /**
     * Asyncs perform this operation on the given argument.
     *
     * @param t the input argument
     * @param completedFuture represents this operation result.
     */
    void accept(T t, CompletableFuture<U> completedFuture);
}
