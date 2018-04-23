package ehb.finalwork.manager.error;

public class LoginException extends Exception{
    public LoginException() {
        super("Invalid username or Password");
    }

    public LoginException(String message) {
        super(message);
    }
}
