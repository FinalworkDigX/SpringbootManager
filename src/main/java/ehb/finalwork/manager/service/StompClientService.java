package ehb.finalwork.manager.service;

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

@Service
public class StompClientService {
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    @EventListener(ApplicationReadyEvent.class)
    public void startStompClient() {
        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);
        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);


        String url = "ws://127.0.0.1:9000/managerWS";
        StompSessionHandler sessionHandler = new SessionHandler();
        stompClient.connect(url, sessionHandler);
        log.warn("connection");
    }

//    public void startStompClient() throws ExecutionException, InterruptedException {
//        ServiceClient serviceClient = new ServiceClient();
//
//        ListenableFuture<StompSession> f = serviceClient.connect();
//        log.warn("after f");
//        StompSession stompSession = f.get();
//        log.warn("after f.get");
//
//        serviceClient.subscribeToChannels(stompSession);
//    }
}
