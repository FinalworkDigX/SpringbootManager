package ehb.finalwork.manager.error;

public class WebSocketException extends Exception {
    private String broadcastChannel;

    public WebSocketException(String broadcastChannel, String message) {
        super(message);
        this.broadcastChannel = broadcastChannel;
    }

    public String getChannel() {
        return broadcastChannel;
    }
}
