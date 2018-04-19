package ehb.finalwork.manager.websockets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ehb.finalwork.manager.model.DataDestination;
import ehb.finalwork.manager.service.DataConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class SessionHandler extends StompSessionHandlerAdapter {
    private Logger log = LoggerFactory.getLogger(getClass());
    private List<DataDestination> destinations;

    @Autowired
    private DataConversionService dataConversionService;

    public SessionHandler() {
    }

    public void setDestinations(List<DataDestination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        for (Object dd : this.destinations) {
            DataDestination dest = new DataDestination();
            if (dd instanceof DataDestination) {
                dest = (DataDestination) dd;
                log.info("Destination added: " + dest.getDestination());
            }
            else if (dd instanceof HashMap) {
                dest = new DataDestination((HashMap<String, Object>) dd);
                destinations.remove(dd);
                destinations.add(dest);

                log.warn("Destination ReQL issue => List<DataDestination> filled with HashMaps....?");
            }
            session.subscribe(dest.getDestination(), this);
        }
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
        log.info("Headers: {}", headers);
        try {
            byte[] jsonBytes = (byte[]) payload;
            HashMap<String, Object> hm = testJackson(jsonBytes);
            String headerDestination = headers.getDestination();

            Optional<DataDestination> destinationOptional = this.destinations.stream().filter(d -> d.getDestination().equals(headerDestination)).findFirst();

            if (destinationOptional.isPresent()) {
                DataDestination dd = destinationOptional.get();
                dataConversionService.convertData(hm, dd.getConversionScheme());
            }
            else {
                log.error("Headers not found! :{}", headerDestination);
            }
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
