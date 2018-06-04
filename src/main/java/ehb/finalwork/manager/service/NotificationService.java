package ehb.finalwork.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final SimpMessagingTemplate webSocket;

    @Autowired
    public NotificationService(SimpMessagingTemplate webSocket) {
        this.webSocket = webSocket;
    }

    public void broadcastNotification(String channel, Object notification) {
        webSocket.convertAndSend("/topic/notification/" + channel, notification);
    }
}
