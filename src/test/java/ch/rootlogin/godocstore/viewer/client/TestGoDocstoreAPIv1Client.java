package ch.rootlogin.godocstore.viewer.client;

import ch.rootlogin.godocstore.viewer.client.exception.ApiException;
import org.junit.Test;

public class TestGoDocstoreAPIv1Client {

    @Test
    public void testLogin() throws ApiException {
        var client = new GoDocstoreAPIv1Client(
                "http://localhost:8000",
                "admin",
                "admin"
        );
    }
}
