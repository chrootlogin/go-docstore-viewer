package ch.rootlogin.godocstore.viewer.connections;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ResourceURLConnection extends URLConnection {
    public ResourceURLConnection(URL url){
        super(url);
    }

    @Override public void connect() throws IOException {}

    @Override public InputStream getInputStream() {
        return getClass().getResourceAsStream(url.getFile());
    }
}
