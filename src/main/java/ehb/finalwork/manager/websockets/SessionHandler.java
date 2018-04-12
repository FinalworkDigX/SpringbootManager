package ehb.finalwork.manager.websockets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.*;

import java.io.IOException;
import java.lang.reflect.Type;
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
        return byte[].class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        try {
            byte[] jsonBytes = (byte[]) payload;
            HashMap<String, Object> hm = testJackson(jsonBytes);
        }
        catch (IOException e) {
            log.error("handleFrame error: {}", e);
        }
    }

    private HashMap<String, Object> testJackson(byte[] o) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
        return mapper.readValue(o, typeRef);
    }
}
