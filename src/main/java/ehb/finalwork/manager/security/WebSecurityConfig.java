package ehb.finalwork.manager.security;

import com.auth0.exception.Auth0Exception;
import ehb.finalwork.manager.model.AuthAPIWrapper;
import ehb.finalwork.manager.model.MgmtApi;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

@Configuration
@EnableWebSecurity
@PropertySource(value = "classpath:security.properties", ignoreResourceNotFound = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${auth0.apiAudience:}")
    private String apiAudience;
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
        http.csrf().disable()
            .authorizeRequests().anyRequest().permitAll();

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

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(ImmutableList.of("*"));
//        configuration.setAllowedMethods(ImmutableList.of("HEAD",
//                "GET", "POST", "PUT", "DELETE", "PATCH"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type", "Accept", "Origin", "X-Auth-Token"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthAPIWrapper authAPIWrapper() {
        if (isNullOrEmpty(clientId) ||
                isNullOrEmpty(clientSecret) ||
                isNullOrEmpty(apiConnection) ||
                isNullOrEmpty(apiAudience)
                ) {
            log.warn("Default AuthAPI is being used, check if security.properties is present and has all required information.");
        }

        return new AuthAPIWrapper(domain, clientId, clientSecret, apiConnection, apiAudience);
    }

    @Bean
    public MgmtApi mgmtApi() throws Auth0Exception {
        // Return unusable Wrapper if no properties present
        if (isNullOrEmpty(managementConnection) ||
                isNullOrEmpty(clientId) ||
                isNullOrEmpty(clientSecret) ||
                isNullOrEmpty(issuer)
                ) {
            log.warn("Default ManagementAPI is being used, check if security.properties is present and has all required information.");
            throw new Auth0Exception("Security properties not present");
        }
        // Setup tokenRequestHeaders
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        HashMap<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put("grant_type", managementConnection);
        jsonMap.put("client_id", clientId);
        jsonMap.put("client_secret", clientSecret);
        jsonMap.put("audience", issuer + "api/v2/");

        JSONObject json = new JSONObject(jsonMap);
        HttpEntity<String> httpEntity = new HttpEntity<String>(json.toString(), httpHeaders);

        String tokenIssuer = issuer + "oauth/token";

        return new MgmtApi(domain, apiConnection, tokenIssuer, httpEntity);
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}

