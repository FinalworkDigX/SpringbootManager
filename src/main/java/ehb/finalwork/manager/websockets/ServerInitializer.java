package ehb.finalwork.manager.websockets;

import ehb.finalwork.manager.service.StompClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerInitializer implements ApplicationRunner {

    @Autowired
    StompClientService clientService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        StompClientService clientService = new StompClientService();
//        clientService.startStompClient();
        clientService.startStompClient();
    }
}
