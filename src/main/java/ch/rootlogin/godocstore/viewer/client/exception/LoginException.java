package ch.rootlogin.godocstore.viewer.client.exception;

public class LoginException extends ApiException {
    private final static String error = "Login failure!";

    public LoginException() {
        super(error);
    }
}
