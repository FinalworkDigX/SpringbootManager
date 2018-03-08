package ehb.finalwork.manager.dto;

import com.auth0.json.mgmt.users.User;

public class Auth0UserDto extends User {
    private String errorMessage;

    public Auth0UserDto() {
    }

    public Auth0UserDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
