package ehb.finalwork.manager.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

//    //Begin test for
//    @Autowired
//    private WebSocketMessageBrokerStats webSocketMessageBrokerStats;
//
//    @PostConstruct
//    public void init() {
//        webSocketMessageBrokerStats.setLoggingPeriod(10 * 60000); // desired time in millis
//    }

    /**
     * Set slug to use as prefix for client to listen for updates w/ websockets
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // TODO: 06-01-18 go to single registry endpoint w/ multiple topics
        registry.addEndpoint("/managerWS").withSockJS();
    }

}
