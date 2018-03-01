package ehb.finalwork.manager.dto;

import com.auth0.json.auth.CreatedUser;

public class Auth0CreateUserDto extends CreatedUser {
    private String errorMessage;

    public Auth0CreateUserDto() {
    }

    public Auth0CreateUserDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
