package io.sandstorm.exchange.helper;

public final class ServerAddress {
    private String host;
    private int port;

    public ServerAddress(String host, int port) {
        if (host == null || host.trim().isEmpty()) {
            throw new IllegalArgumentException("host is blank");
        }
        if (port < 0) {
            throw new IllegalArgumentException("Expected port >= 0, actual: " + port);
        }

        this.host = host;
        this.port = port;
    }

    public String host() {
        return this.host;
    }

    public int port() {
        return this.port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerAddress)) return false;

        ServerAddress that = (ServerAddress) o;

        if (port != that.port) return false;
        return host.equals(that.host);
    }

    @Override
    public int hashCode() {
        int result = host.hashCode();
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return host + ":" + port;
    }
}
