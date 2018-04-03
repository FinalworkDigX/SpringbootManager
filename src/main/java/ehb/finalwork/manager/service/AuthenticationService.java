package ehb.finalwork.manager.service;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.CreatedUser;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;
import com.auth0.net.SignUpRequest;
import ehb.finalwork.manager.dto.*;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.error.HiddenException;
import ehb.finalwork.manager.error.LoginException;
import ehb.finalwork.manager.error.SignupException;
import ehb.finalwork.manager.model.AuthAPIWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    @Autowired
    private AuthAPIWrapper auth;

    public CreatedUser signup(Auth0LoginDto auth0LoginDto) throws SignupException {

        Map<String, String> fields = new HashMap<>();
        fields.put("channel", UUID.randomUUID().toString());

        SignUpRequest request = auth.signUp(auth0LoginDto.getEmail(), auth0LoginDto.getPassword(), auth.getConnection()).setCustomFields(fields);
        try {
            return request.execute();
        } catch (Auth0Exception exception) {
            log.error(exception.getMessage());
            throw new SignupException("username or password is invalid");
        }
    }

    public TokenHolder login(Auth0LoginDto auth0LoginDto) throws LoginException {
        // Check if app user of admin account
        String scope = "openid scope:admin";

        AuthRequest request = auth.login(auth0LoginDto.getEmail(), auth0LoginDto.getPassword(), auth.getConnection())
                .setAudience(auth.getAudience())
                .setScope(scope);
        try {
            return request.execute();
        }
        catch (Auth0Exception exception) {
            log.error(exception.getMessage());
            throw new LoginException("Username or password invalid");
        }
    }

    public void resetPassword(Auth0LoginDto auth0LoginDto) {
        Request request = auth.resetPassword(auth0LoginDto.getEmail(), auth.getConnection());
        try {
            request.execute();
        }
        catch (Auth0Exception exception) {
            log.error(exception.getMessage());
        }
    }
}
