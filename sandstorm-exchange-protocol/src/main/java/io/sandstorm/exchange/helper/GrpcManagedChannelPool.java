package io.sandstorm.exchange.helper;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * A simple {@link ManagedChannel} pool that holds the channels to different servers.
 */
public class GrpcManagedChannelPool {

    private static final Logger logger = Logger.getLogger(GrpcManagedChannelPool.class.getName());

    private static final int DEFAULT_TIMEOUT_SECONDS = 2;

    private static final ConcurrentHashMap<ServerAddress, ManagedChannel> channelMap = new ConcurrentHashMap<>();

    private GrpcManagedChannelPool() {
    }

    public static ManagedChannel getChannel(ServerAddress serverAddress) {
        ManagedChannel channel = channelMap.computeIfAbsent(serverAddress, address ->
            ManagedChannelBuilder.forAddress(address.host(), address.port())
                // Channels are secure by default (via SSL/TLS).
                // For the example we disable TLS to avoid needing certificates.
                .usePlaintext(true)
                .build());
        return channel;
    }

    public static void closeChannel(ServerAddress serverAddress, long timeoutSeconds) {
        ManagedChannel theChannel = channelMap.remove(serverAddress);
        if (theChannel != null) {
            closeChannel(theChannel, serverAddress, timeoutSeconds);
        }
    }

    public static void closeAllChannels(final long timeoutSeconds) {
        if (channelMap != null && !channelMap.isEmpty()) {
            channelMap.entrySet().stream().forEach(entry -> closeChannel(entry.getValue(), entry.getKey(), timeoutSeconds));
            channelMap.clear();
        }
    }

    public void destroy() {
        closeAllChannels(DEFAULT_TIMEOUT_SECONDS);
    }

    private static void closeChannel(ManagedChannel channel, ServerAddress serverAddress, long timeoutSeconds) {
        if (timeoutSeconds < 0 || timeoutSeconds >= Integer.MAX_VALUE) {
            timeoutSeconds = DEFAULT_TIMEOUT_SECONDS;
        }

        final long realTimeout = timeoutSeconds;

        try {
            boolean terminated = channel.shutdown().awaitTermination(realTimeout, TimeUnit.SECONDS);
            if (terminated) {
                logger.info(String.format("ManagedChannel to [%s] shutdown successfully.", serverAddress));
            } else {
                // TODO: 31/08/2017 Do something, for example: put the channel into a queue, and a async task will try closing it again.
            }
        } catch (InterruptedException e) {
            logger.severe(String.format("InterruptedException occurred while shutting down a ManagedChannel to [%s].", serverAddress));
            Thread.currentThread().interrupt();
        }
    }

}
