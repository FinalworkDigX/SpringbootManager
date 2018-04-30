package ehb.finalwork.manager.model;

import com.auth0.client.mgmt.ManagementAPI;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class MgmtApi {
    private ManagementAPI managementAPI;
    private String connection;
    private String tokenIssuer;
    private String domain;
    private HttpEntity<String> tokenRequestHeaders;


    public MgmtApi(String domain, String connection, String tokenIssuer, HttpEntity<String> tokenRequestHeaders) {
        this.domain = domain;
        this.tokenIssuer = tokenIssuer;
        this.tokenRequestHeaders = tokenRequestHeaders;
        this.connection = connection;

        this.resetManagementAPI();
    }

    public void resetManagementAPI() {
        RestTemplate restTemplate = new RestTemplate();
        OauthToken token = restTemplate.postForObject(tokenIssuer, tokenRequestHeaders, OauthToken.class);
        this.managementAPI = new ManagementAPI(domain, token.getAccessToken());
    }

    public String getConnection() {
        return connection;
    }

    public ManagementAPI getManagementAPI() {
        return managementAPI;
    }
}
