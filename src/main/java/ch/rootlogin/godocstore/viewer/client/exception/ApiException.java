package ch.rootlogin.godocstore.viewer.client.exception;

public class ApiException extends Exception {
    public ApiException(String error) {
        super(error);
    }
}
