package ehb.finalwork.manager.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfiguration {

    @Bean
    public RoomDao roomDao() {
        return new RoomDaoImpl();
    }

    @Bean
    public DataLogDao dataLogDao() {
        return new DataLogDaoImpl();
    }

    @Bean
    public DataItemDao dataItemDao() {
        return new DataItemImpl();
    }

    @Bean
    public BeaconDao beaconDao() {
        return new BeaconDaoImpl();
    }
}
