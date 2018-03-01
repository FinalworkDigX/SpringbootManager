package ehb.finalwork.manager.controller;

import com.auth0.json.mgmt.users.User;
import ehb.finalwork.manager.service.Auth0ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/management")
public class Auth0ManagementController {

    @Autowired
    private Auth0ManagementService managementService;

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return managementService.createUser(user);
    }

    @PutMapping("/user")
    public User update(@RequestBody User user) {
        return managementService.updateUser(user);
    }

    @DeleteMapping("/user/{uid}")
    public User delete(@PathVariable String uid) {
        return managementService.deleteUser(uid);
    }
}
