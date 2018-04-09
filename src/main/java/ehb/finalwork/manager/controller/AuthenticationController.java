package ehb.finalwork.manager.controller;

import com.auth0.json.auth.CreatedUser;
import com.auth0.json.auth.TokenHolder;
import ehb.finalwork.manager.dto.Auth0LoginDto;
import ehb.finalwork.manager.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public CreatedUser signup(@RequestBody Auth0LoginDto auth0LoginDto) throws Exception {
        return authenticationService.signup(auth0LoginDto);
    }

    @PostMapping("{type}/login")
    public TokenHolder login(@PathVariable String type, @RequestBody Auth0LoginDto auth0LoginDto) throws Exception {
        return authenticationService.login(type, auth0LoginDto);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("authorization") String authorization) throws Exception {
        authenticationService.logout(authorization.split(" ")[1]);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody Auth0LoginDto auth0LoginDto) {
        authenticationService.resetPassword(auth0LoginDto);
    }
}
