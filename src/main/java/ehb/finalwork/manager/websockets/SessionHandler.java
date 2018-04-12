package ehb.finalwork.manager.websockets;

import com.sun.org.apache.xpath.internal.operations.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SessionHandler extends StompSessionHandlerAdapter {
    private Logger log = LoggerFactory.getLogger(getClass());

    public SessionHandler() {
        log.error("====SessionHandler====");
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.warn("__________________________________________________________");
        session.subscribe("/topic/echo", this);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Object.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        // Need to change class as to recieve json
        log.info("Received: {}", payload);
    }
}
