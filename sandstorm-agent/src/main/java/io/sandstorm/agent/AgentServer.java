package io.sandstorm.agent;

import io.sandstorm.agent.domain.AgentConfig;
import io.sandstorm.agent.portadapter.gateway.in.AgentService;
import io.sandstorm.agent.portadapter.gateway.out.ControllerClient;
import io.sandstorm.exchange.helper.GrpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * A sample gRPC server that serve the  AgentService (see agent_service.proto) service.
 */
public class AgentServer {

    private static final Logger logger = LoggerFactory.getLogger(AgentServer.class);

    public static void main(String[] args) {
        // Register this task-runner to the controller
        logger.info("****** Agent server is starting ******");
        asyncRegisterLoadInjector();
        startAgentServer();
    }

    private static void startAgentServer() {
        int port = AgentConfig.agentPort().intValue();
        GrpcServer server = new GrpcServer(port);
        try {
            server.addService(new AgentService());
            server.start();
            logger.info("****** Agent server was stopped ******");
        } catch (IOException e) {
            logger.error("Server failed, listening on " + port);
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            logger.error("Server failed, listening on " + port);
            logger.error(e.getMessage(), e);
        }
    }

    private static void asyncRegisterLoadInjector() {
        Thread serverRunner = new Thread(() -> {
            try {
                ControllerClient.getInstance().registerLoadInjector();
            } catch (Exception e) {
                logger.warn("Warning: Register this loadInjector to controller failed, errorMsg = {}", e.getMessage());
            }
        });
        serverRunner.setDaemon(true);
        serverRunner.start();
    }

}
