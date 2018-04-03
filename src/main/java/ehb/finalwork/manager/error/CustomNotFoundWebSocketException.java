package ehb.finalwork.manager.error;

public class CustomNotFoundWebSocketException extends WebSocketException {

    public CustomNotFoundWebSocketException(String broadcastChannel, String message) {
        super(broadcastChannel, message);
    }
}
