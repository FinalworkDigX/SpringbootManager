package ehb.finalwork.manager.model;

import com.auth0.client.mgmt.ManagementAPI;

public class MgmtAPIWrapper extends ManagementAPI {

    private String connection;

    public MgmtAPIWrapper(String domain, String apiToken, String connection) {
        super(domain, apiToken);

        this.connection = connection;
    }

    public String getConnection() {
        return connection;
    }
}
