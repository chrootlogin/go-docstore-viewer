package ch.rootlogin.godocstore.viewer.models;

public class ServerConnection {
    private String url;
    private String username;
    private String password;

    public ServerConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public ServerConnection setUrl(String url) {
        this.url = url;

        return this;
    }

    public String getUsername() {
        return username;
    }

    public ServerConnection setUsername(String username) {
        this.username = username;

        return this;
    }

    public String getPassword() {
        return password;
    }

    public ServerConnection setPassword(String password) {
        this.password = password;

        return this;
    }
}
