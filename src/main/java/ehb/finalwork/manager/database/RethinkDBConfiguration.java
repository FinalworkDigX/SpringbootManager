package ehb.finalwork.manager.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:rethink.properties", ignoreResourceNotFound=true)
public class RethinkDBConfiguration {

    @Value("${rethink.username:test_account}")
    private String username;
    @Value("${rethink.password:}")
    private String password;
    @Value("${rethink.host:db.ludovicmarchand.be}")
    private String host;

    @Bean
    public RethinkDBConnectionFactory connectionFactory() {
        return RethinkDBConnectionFactory.getInstance(host, username, password);
    }

    @Bean
    DBInitializer dbInitializer() {
        return new DBInitializer();
    }
}
