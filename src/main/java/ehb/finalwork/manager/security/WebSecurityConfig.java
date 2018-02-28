package ehb.finalwork.manager.security;

import com.auth0.client.auth.AuthAPI;
import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import ehb.finalwork.manager.model.AuthAPIWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@PropertySource(value = "classpath:security.properties", ignoreResourceNotFound=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
    @Value("${auth0.apiAudience:}")
    private String audience;

    @Value("${auth0.issuer:}")
    private String issuer;

    @Value(value = "${auth0.connection:}")
    private String connection;
    @Value(value = "${auth0.domain:}")
    private String domain;
    @Value(value = "${auth0.clientId:}")
    private String clientId;
    @Value(value = "${auth0.clientSecret:}")
    private String clientSecret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtWebSecurityConfigurer
                .forRS256(audience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/login").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("read:api")
//                .antMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("create:api")
//                .antMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("update:api")
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyAuthority("delete:api")
                .anyRequest().authenticated();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthAPIWrapper authAPIWrapper() {
        return new AuthAPIWrapper(domain, clientId, clientSecret, connection, audience);
    }
}

