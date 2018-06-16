package io.sandstorm.exchange.helper;

import java.util.Optional;

@FunctionalInterface
public interface LocalInvocation<R> {

    Optional<R> invoke();

}
