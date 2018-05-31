package ehb.finalwork.manager.error;

public class MissingIdException extends Exception{
    public MissingIdException() {
        super("id parameter missing from request");
    }

    public MissingIdException(String message) {
        super(message);
    }
}
