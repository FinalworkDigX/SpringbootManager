package ehb.finalwork.manager.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:rethink.properties")
@ConfigurationProperties(prefix = "rethink")
public class RethinkDBConfiguration {
//    public static final String DBHOST = "10.3.50.6";
    private static final String DBHOST = "192.168.0.115";
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bean
    public RethinkDBConnectionFactory connectionFactory() {
        return new RethinkDBConnectionFactory(DBHOST, username, password);
    }

    @Bean
    DBInitializer dbInitializer() {
        return new DBInitializer();
    }
}
