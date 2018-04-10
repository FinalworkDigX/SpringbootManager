package ehb.finalwork.manager.controller;

import com.auth0.json.mgmt.users.User;
import com.auth0.json.mgmt.users.UsersPage;
import ehb.finalwork.manager.service.Auth0ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/management/user")
public class Auth0ManagementController {

    @Autowired
    private Auth0ManagementService managementService;

    @GetMapping()
    public List<User> getAll() {
        return managementService.getUsers();
    }

    @PostMapping()
    public User create(@RequestBody User user) {
        return managementService.createUser(user);
    }

    @PutMapping()
    public User update(@RequestBody User user) {
        return managementService.updateUser(user);
    }

    @DeleteMapping("/{uid}")
    public User delete(@PathVariable String uid) {
        return managementService.deleteUser(uid);
    }
}
