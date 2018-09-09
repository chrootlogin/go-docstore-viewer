package ch.rootlogin.godocstore.viewer.models;

import java.time.LocalDateTime;

public class AuthToken {
    private String token;
    private LocalDateTime expire;

    public AuthToken(String token, LocalDateTime expire) {
        this.token = token;
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpire() {
        return expire;
    }
}
