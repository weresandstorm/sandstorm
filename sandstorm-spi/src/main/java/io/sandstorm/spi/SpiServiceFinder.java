package io.sandstorm.spi;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public final class SpiServiceFinder {

    private static final Map<Class, Object> servicesCache = new ConcurrentHashMap<>(10);

    private SpiServiceFinder() {
    }

    public static <S> S findService(Class<S> serviceClass) {
        S theService = (S) servicesCache.computeIfAbsent(serviceClass, (kServiceClass) -> {
            ServiceLoader<S> serviceLoader = ServiceLoader.load(kServiceClass);
            for (S service : serviceLoader) {
                return service;
            }
            return null;
        });

        if (theService == null) {
            throw new RuntimeException(String.format("Not found service implementing spi %s", serviceClass));
        } else {
            return theService;
        }
    }
}
