package ch.rootlogin.godocstore.viewer.client;

import ch.rootlogin.godocstore.viewer.client.exception.LoginException;
import ch.rootlogin.godocstore.viewer.models.AuthToken;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoDocstoreAPIv1Client {
    private final String urlBase = "/api/v1";

    private AuthToken authToken;
    private String docstoreHost;

    private String username;
    private String password;

    private final static Logger logger = Logger.getLogger(GoDocstoreAPIv1Client.class.getName());

    public GoDocstoreAPIv1Client(String docstoreHost, String username, String password) throws LoginException {
        this.docstoreHost = docstoreHost;

        this.username = username;
        this.password = password;

        login();
    }

    private void login() throws LoginException {
        try {
            var loginJson = new JSONObject();
            loginJson.put("username", this.username);
            loginJson.put("password", this.password);

            var req = Unirest.post(getURL("/user/login"))
                    .body(loginJson)
                    .asJson();

            if(req.getStatus() == 200) {
                var expire = req.getBody().getObject().getString("expire");
                var token = req.getBody().getObject().getString("token");

                // parse date
                var dateTime = LocalDateTime.parse(expire, DateTimeFormatter.ISO_DATE_TIME);

                authToken = new AuthToken(token, dateTime);

                logger.log(Level.INFO, String.format("New login token received, expiring: %s", dateTime));

                return;
            }
        } catch(UnirestException ex) {
            logger.log(Level.WARNING, "Error logging in.", ex);
        }

        throw new LoginException();
    }

    private String getURL(String path) {
        return docstoreHost + path;
    }
}