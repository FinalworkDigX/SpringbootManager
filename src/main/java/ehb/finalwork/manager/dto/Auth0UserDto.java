package ehb.finalwork.manager.dto;

import com.auth0.json.auth.CreatedUser;

public class Auth0UserDto extends CreatedUser {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
