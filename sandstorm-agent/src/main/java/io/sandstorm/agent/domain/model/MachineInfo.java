package io.sandstorm.agent.domain.model;

import io.sandstorm.agent.domain.AgentConfig;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MachineInfo {

    private static final Logger logger = LoggerFactory.getLogger(MachineInfo.class);

    private String hostname;
    private String localIP;
    private int port;

    public MachineInfo() {
    }

    public void loadInfo() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            this.hostname = address.getHostName();
            this.localIP = localHost();
            this.port = AgentConfig.agentPort();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public String getHostname() {
        return hostname;
    }

    public String getLocalIP() {
        return localIP;
    }

    public int getPort() {
        return port;
    }

    private String localHost() {
        try {
            String localhost = InetAddress.getLocalHost().getHostAddress();
            if (!StringUtils.isEmpty(localhost) && !"127.0.0.1".equals(localhost)) {
                return localhost;
            }
            Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
            while (n.hasMoreElements()) {
                NetworkInterface e = n.nextElement();
                if (!"eth0".equals(e.getName())) {
                    continue;
                }
                Enumeration<InetAddress> a = e.getInetAddresses();
                while (a.hasMoreElements()) {
                    localhost = a.nextElement().getHostAddress();
                }
            }
            return localhost;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
