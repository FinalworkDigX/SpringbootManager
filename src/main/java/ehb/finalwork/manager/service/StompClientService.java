package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dto.InformationConversionDto;
import ehb.finalwork.manager.model.DataDestination;
import ehb.finalwork.manager.websockets.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    SessionHandler sessionHandler;

//    private List<StompClient> stompClients;
    private List<WebSocketStompClient> stompClients;
    private final String testUrl = "ws://127.0.0.1:9000/managerWS";


    public StompClientService() {
        stompClients = new ArrayList<>();
    }

    public void startStompClient() {

        // Setup convertScheme
        HashMap<String, Object> tempMap = new HashMap<>();
        tempMap.put("id", "item_id");
        tempMap.put("type", new InformationConversionDto("Kind: ", 1L));
        tempMap.put("use_info.on_time", new InformationConversionDto("On: ", 2L));
        tempMap.put("use_info.temp", new InformationConversionDto("Temp: ", 3L));

        // Setup dataSources
        List<DataDestination> tempList = new ArrayList<>();
        tempList.add(new DataDestination("/topic/echo", tempMap));


        // SetupClient
        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);
        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        // Start client
        sessionHandler.setDestinations(tempList);
        stompClient.connect(testUrl, sessionHandler);


        
    }


}
