package ehb.finalwork.manager.controller;

import com.auth0.json.auth.CreatedUser;
import com.auth0.json.auth.TokenHolder;
import ehb.finalwork.manager.dto.Auth0LoginDto;
import ehb.finalwork.manager.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public CreatedUser signup(@RequestBody Auth0LoginDto auth0LoginDto) {
        return authenticationService.signup(auth0LoginDto);
    }

    @PostMapping("/login")
    public TokenHolder login(@RequestBody Auth0LoginDto auth0LoginDto) {
        return authenticationService.login(auth0LoginDto);
    }

    @PostMapping("/reset-password")
    public CreatedUser resetPassword(@RequestBody Auth0LoginDto auth0LoginDto) {
        return authenticationService.resetPassword(auth0LoginDto);
    }
}
