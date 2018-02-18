package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.model.DataLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChangeListenerConfiguration {

    @Bean(name = "DataLogChangeListener")
    public ChangeListener dataLogChangeListener() {
        ChangeListener cl = new ChangeListener();
        cl.setModel(new RethinkDataLogDto());
        return cl;
    }

    @Bean(name = "RoomChangeListener")
    public ChangeListener roomChangeListener() {
        ChangeListener cl = new ChangeListener();
        cl.setModel(new RethinkRoomDto());
        return cl;
    }
}
