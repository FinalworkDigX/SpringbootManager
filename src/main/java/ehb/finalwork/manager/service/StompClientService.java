package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dto.InformationConversionDto;
import ehb.finalwork.manager.model.DataDestination;
import ehb.finalwork.manager.model.Information;
import ehb.finalwork.manager.model.StompClient;
import ehb.finalwork.manager.websockets.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class StompClientService {
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    private List<StompClient> stompClients;

    StompClientService() {
        stompClients = new ArrayList<>();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startStompClient() {

        HashMap<String, Object> tempMap = new HashMap<>();
        tempMap.put("id", "id");
        tempMap.put("type", new InformationConversionDto("Kind: ", 1L));
        tempMap.put("use_info.on_time", new InformationConversionDto("On: ", 2L));
        tempMap.put("use_info.temp", new InformationConversionDto("Temp: ", 3L));


        List<DataDestination> tempList = new ArrayList<>();
        tempList.add(new DataDestination("/topic/echo", tempMap));

        // Get url from Rethink
        StompClient sc = new StompClient("ws://127.0.0.1:9000/managerWS", tempList);
        sc.connect();
        stompClients.add(sc);
    }

    public List<StompClient> getStompClients() {
        return stompClients;
    }
}
