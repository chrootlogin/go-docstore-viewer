package ch.rootlogin.godocstore.viewer.models;

import java.util.List;

public class Configuration {
    private List<ServerConnection> connections;

    public List<ServerConnection> getConnections() {
        return connections;
    }

    public void setConnections(List<ServerConnection> connections) {
        this.connections = connections;
    }
}
