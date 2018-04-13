package ehb.finalwork.manager.model;

import ehb.finalwork.manager.service.RoomService;
import ehb.finalwork.manager.service.StompClientService;
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


import java.util.Collections;
import java.util.List;

public class StompClient {
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    private String url;
    private List<DataDestination> destinations;
    private WebSocketStompClient stompClient;

    public StompClient(String url, List<DataDestination> destinations) {
        this.url = url;
        this.destinations = destinations;
        this.commonInit();
    }

    public StompClient(DataSource ds) {
        this.url = ds.getUrl();
        this.destinations = ds.getDestination();
        this.commonInit();
    }

    private void commonInit() {
        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);
        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());
        this.stompClient = new WebSocketStompClient(sockJsClient);
    }

    public void connect() {
        StompSessionHandler sessionHandler = new SessionHandler(destinations);
        stompClient.connect(url, sessionHandler);
    }
}
