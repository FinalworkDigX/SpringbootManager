package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfiguration {

    @Bean
    public RoomDao roomDao() {
        return new RoomDaoImpl(new Room());
    }

    @Bean
    public BeaconDao beaconDao() {
        return new BeaconDaoImpl(new Beacon());
    }

    @Bean
    public DataLogDao dataLogDao() {
        return new DataLogDaoImpl(new DataLog());
    }

    @Bean
    public DataItemDao dataItemDao() {
        return new DataItemImpl(new DataItem());
    }

    @Bean
    public DataSourceDao dataSourceDao() {
        return new DataSourceDaoImpl(new DataSource());
    }
}
