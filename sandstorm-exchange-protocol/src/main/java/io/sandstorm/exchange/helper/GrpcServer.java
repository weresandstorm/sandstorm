package io.sandstorm.exchange.helper;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A server that exposes grpc-based services and serves grpc calls.
 */
public class GrpcServer {

    private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());

    private final int port;
    private Server server;
    private ServerBuilder<?> serverBuilder;
    private final AtomicBoolean started = new AtomicBoolean(false);

    public GrpcServer(int port) {
        this.port = port;
        serverBuilder = ServerBuilder.forPort(port);
    }

    public final void addService(BindableService ...services) {
        for (BindableService service : services) {
            serverBuilder.addService(service);
        }
    }

    public final void start() throws IOException, InterruptedException {
        if (started.compareAndSet(false, true)) {
            this.server = serverBuilder.build();
            this.server.start();
            logger.info("Server started, listening on " + port);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    // Use stderr here since the logger may has been reset by its JVM shutdown hook.
                    System.err.println("*** shutting down gRPC server since JVM is shutting down");
                    GrpcServer.this.shutdown();
                    System.err.println("*** server shut down");
                }
            });

            this.blockUntilShutdown();
        } else {
            logger.log(Level.WARNING, "Server has already started, should not be started again!");
        }
    }

    /**
     * Stop serving requests and shutdown resources.
     */
    public final void shutdown() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
