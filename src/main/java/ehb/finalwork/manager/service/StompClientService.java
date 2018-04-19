package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dto.InformationConversionDto;
import ehb.finalwork.manager.model.DataDestination;
import ehb.finalwork.manager.model.DataSource;
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

    @Autowired
    DataSourceService dataSourceService;

    private WebSocketStompClient stompClient;

    public StompClientService() { }

    public void startStompClient() {

        try {
            List<DataSource> dataSourceList = dataSourceService.getAll();

            for (DataSource dataSource : dataSourceList) {
                sessionHandler.setDestinations(dataSource.getDestinations());
                stompClient = createNewStompClient();
                stompClient.connect(dataSource.getUrl(), sessionHandler);
            }
        }
        catch (Exception e) {
            log.error("StartStompClient error: {}", e);
        }
        
    }

    public void addStompClient(DataSource dataSource) {
        sessionHandler.setDestinations(dataSource.getDestinations());
        stompClient = createNewStompClient();
        stompClient.connect(dataSource.getUrl(), sessionHandler);
    }

    private WebSocketStompClient createNewStompClient() {
        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);
        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());
        return new WebSocketStompClient(sockJsClient);
    }


}
