package ehb.finalwork.manager.dto;

public class Auth0LoginDto {
    private String email;
    private String password;

    public Auth0LoginDto() {
    }

    public Auth0LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
