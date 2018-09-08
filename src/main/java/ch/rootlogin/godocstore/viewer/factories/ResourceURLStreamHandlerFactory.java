package ch.rootlogin.godocstore.viewer.factories;

import ch.rootlogin.godocstore.viewer.connections.ResourceURLConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class ResourceURLStreamHandlerFactory implements URLStreamHandlerFactory {
    URLStreamHandler streamHandler = new URLStreamHandler(){
        @Override protected URLConnection openConnection(URL url) throws IOException {
            if (url.toString().toLowerCase().endsWith(".css")) {
                return new ResourceURLConnection(url);
            }
            throw new FileNotFoundException();
        }
    };
    @Override public URLStreamHandler createURLStreamHandler(String protocol) {
        if ("internal".equals(protocol)) {
            return streamHandler;
        }
        return null;
    }
}
