package io.sandstorm.exchange.helper;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GrpcClient {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected final ServerAddress serverAddress;
    protected final ManagedChannel channel;

    protected GrpcClient(ServerAddress serverAddress) {
        this.serverAddress = serverAddress;
        channel = ManagedChannelBuilder.forAddress(serverAddress.host(), serverAddress.port())
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true)
                .build();
        logger.log(Level.INFO,
                "Created {0} with server address {1}",
                new Object[]{ this.getClass(), serverAddress });
    }

    public void shutdown() {
        try {
            final long timeout = 5;
            boolean terminated = channel.shutdown().awaitTermination(timeout, TimeUnit.SECONDS);
            if (!terminated) {
                logger.log(Level.WARNING,
                        "Waited {0} seconds for termination of channel to [{1}] until timeout," +
                                " but the channel had not been shutdown",
                        new Object[]{timeout, serverAddress});
                // TODO: 01/09/2017 For this case, should do some more things for the channel?
            } else {
                logger.log(Level.INFO,
                        "Successfully shut down {0} with server address {1}",
                        new Object[]{this.getClass(), serverAddress});
            }
        } catch (InterruptedException e) {
            logger.log(Level.WARNING,
                    "Interrupted while shutting down {0} with server address {1}",
                    new Object[]{this.getClass(), serverAddress});
            Thread.currentThread().interrupt();
        }
    }

}
