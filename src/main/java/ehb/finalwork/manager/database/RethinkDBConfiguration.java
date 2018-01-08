package ehb.finalwork.manager.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RethinkDBConfiguration {
    public static final String DBHOST = "10.3.50.6";

    @Bean
    public RethinkDBConnectionFactory connectionFactory() {
        return new RethinkDBConnectionFactory(DBHOST);
    }

    @Bean
    DBInitializer dbInitializer() {
        return new DBInitializer();
    }
}
