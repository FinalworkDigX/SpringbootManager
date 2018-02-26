package ehb.finalwork.manager.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:rethink.properties")
@ConfigurationProperties(prefix = "rethink")
public class RethinkDBConfiguration {
    private String username;
    private String password;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

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
        return RethinkDBConnectionFactory.getInstance(ip, username, password);
    }

    @Bean
    DBInitializer dbInitializer() {
        return new DBInitializer();
    }
}
