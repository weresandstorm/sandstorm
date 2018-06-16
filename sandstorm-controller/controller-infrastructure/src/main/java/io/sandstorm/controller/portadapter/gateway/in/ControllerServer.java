package io.sandstorm.controller.portadapter.gateway.in;

import io.sandstorm.common.AppInternalException;
import io.sandstorm.exchange.helper.GrpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class ControllerServer implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ApplicationContext appCtx;
    private final GrpcServer grpcServer;

    @Value("${controller.grpc.port}")
    private int port = DEFAULT_PORT;
    private static final int DEFAULT_PORT = 6221;

    public ControllerServer() {
        this.grpcServer = new GrpcServer(port);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appCtx = applicationContext;
    }

    @PostConstruct
    private void exposeServices() {
        ControllerService service = appCtx.getBean(ControllerService.class);
        grpcServer.addService(service);
        
        Thread serverRunner = new Thread(() -> {
            try {
                // Call to start will block this thread until shutdown
                ControllerServer.this.grpcServer.start();
                logger.info("Controller server started on port {} successfully", port);
            } catch (IOException e) {
                throw new AppInternalException("Failed to start the controller server!", e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AppInternalException("The thread running controller server was interrupted!", e);
            }
        }, "Controller-Grpc-Server-Runner");
        serverRunner.setDaemon(true);
        serverRunner.start();
    }

    @PreDestroy
    public void releaseResources() {
        this.grpcServer.shutdown();
        logger.info("Controller server shutdown successfully!");
    }

}
