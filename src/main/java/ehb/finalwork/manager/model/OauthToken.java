package ehb.finalwork.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public class OauthToken {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("scope")
    private List<String> scope;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("token_type")
    private String tokenType;

    public OauthToken() {
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return this.accessToken;
    }

    @JsonProperty("scope")
    public List<String> getScope() {
        return this.scope;
    }

    @JsonProperty("scope")
    public void setScope(String scope) {
        this.scope = new ArrayList<String>(Arrays.asList(scope.split(" ")));
    }

    @JsonProperty("expires_in")
    public String getExpiresIn() {
        return this.expiresIn;
    }

    @JsonProperty("token_type")
    public String getTokenType() {
        return this.tokenType;
    }


}
