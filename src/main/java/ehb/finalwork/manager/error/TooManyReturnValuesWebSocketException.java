package ehb.finalwork.manager.error;

public class TooManyReturnValuesWebSocketException extends WebSocketException{

    public TooManyReturnValuesWebSocketException(String broadcastChannel, String message) {
        super(broadcastChannel, message);
    }
}