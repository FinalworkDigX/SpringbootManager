package ehb.finalwork.manager.security;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import com.sun.deploy.net.HttpResponse;
import ehb.finalwork.manager.model.AuthAPIWrapper;
import ehb.finalwork.manager.model.MgmtAPIWrapper;
import ehb.finalwork.manager.model.OauthToken;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@PropertySource(value = "classpath:security.properties", ignoreResourceNotFound=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
    @Value("${auth0.apiAudience:}")
    private String apiAudience;
    @Value("${auth0.managementAudience:}")
    private String managementAudience;
    @Value("${auth0.issuer:}")
    private String issuer;
    @Value(value = "${auth0.apiConnection:}")
    private String apiConnection;
    @Value(value = "${auth0.managementConnection:}")
    private String managementConnection;
    @Value(value = "${auth0.domain:finalwork.eu.auth0.com}")
    private String domain;
    @Value(value = "${auth0.clientId:}")
    private String clientId;
    @Value(value = "${auth0.clientSecret:}")
    private String clientSecret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // For testing
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/**").permitAll()
                .anyRequest().authenticated();

        // To Use
        // TODO: differentiate account with scopes
//        JwtWebSecurityConfigurer
//                .forRS256(apiAudience, issuer)
//                .configure(http)
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/auth/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/v1/**").hasAnyAuthority("scope:app")
//                .antMatchers(HttpMethod.POST, "/v1/**").hasAnyAuthority("scope:app")
//                .antMatchers(HttpMethod.PUT, "/v1/**").hasAnyAuthority("scope:app")
//                .antMatchers(HttpMethod.DELETE, "/v1/**").hasAnyAuthority("scope:admin")
//                .antMatchers(HttpMethod.DELETE, "/v1/management/**").hasAnyAuthority("scope:admin")
//                .anyRequest().authenticated();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthAPIWrapper authAPIWrapper() {
        return new AuthAPIWrapper(domain, clientId, clientSecret, apiConnection, apiAudience);
    }

    @Bean
    public MgmtAPIWrapper mgmtAPIWrapper() {

        // Return unusable Wrapper if no properties present
        if (isNullOrEmpty(managementConnection) ||
                isNullOrEmpty(clientId) ||
                isNullOrEmpty(clientSecret) ||
                isNullOrEmpty(managementAudience) ||
                isNullOrEmpty(issuer)) {
            return new MgmtAPIWrapper(domain, "nope", apiConnection);
        }

        // Return correct Wrapper is properties are present
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put("grant_type", managementConnection);
        json.put("client_id", clientId);
        json.put("client_secret", clientSecret);
        json.put("audience", managementAudience);

        HttpEntity<String> httpEntity = new HttpEntity <String> (json.toString(), httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        OauthToken response = restTemplate.postForObject(issuer + "oauth/token", httpEntity, OauthToken.class);

        return new MgmtAPIWrapper(domain, response.getAccessToken(), apiConnection);
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}

