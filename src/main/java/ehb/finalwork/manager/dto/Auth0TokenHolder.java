package ehb.finalwork.manager.dto;

import com.auth0.json.auth.TokenHolder;

public class Auth0TokenHolder extends TokenHolder {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
