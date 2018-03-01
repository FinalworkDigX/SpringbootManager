package ehb.finalwork.manager.service;

import ehb.finalwork.manager.model.DataLog;
import ehb.finalwork.manager.model.Room;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChangeListenerConfiguration {

    @Bean(name = "DataLogChangeListener")
    public ChangeListener dataLogChangeListener() {
        ChangeListener cl = new ChangeListener();
        cl.setModel(new DataLog());
        return cl;
    }

    @Bean(name = "RoomChangeListener")
    public ChangeListener roomChangeListener() {
        ChangeListener cl = new ChangeListener();
        cl.setModel(new Room());
        return cl;
    }
}
