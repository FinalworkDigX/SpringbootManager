package ehb.finalwork.manager.model;

import com.auth0.client.auth.AuthAPI;

public class AuthAPIWrapper extends AuthAPI {

    private String connection;
    private String audience;

    public AuthAPIWrapper(String domain, String clientId, String clientSecret, String connection, String audience) {
        super(domain, clientId, clientSecret);

        this.connection = connection;
        this.audience = audience;
    }

    public String getConnection() {
        return connection;
    }

    public String getAudience() {
        return audience;
    }
}
