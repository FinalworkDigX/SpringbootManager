package ehb.finalwork.manager.service;

import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import com.auth0.json.mgmt.users.UsersPage;
import com.auth0.net.Request;
import ehb.finalwork.manager.dto.Auth0UserDto;
import ehb.finalwork.manager.model.MgmtApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class Auth0ManagementService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MgmtApi mgmt;

    public List<User> getUsers() throws Auth0Exception {
        UserFilter userFilter = new UserFilter();
        Request request = mgmt.getManagementAPI().users().list(userFilter);
        UsersPage up = (UsersPage) executeQuery(request, true);
        return up.getItems();
    }

    public User createUser(User user) throws Auth0Exception {
        // Set defaults
        user.setConnection(mgmt.getConnection());
        user = this.setDefaults(user);

        Request<User> request = mgmt.getManagementAPI().users().create(user);
        return (User) executeQuery(request, true);
    }

    public User updateUser(User user) throws Auth0Exception {
        // Set defaults
        user = this.setDefaults(user);

        User u = new User();
        u.setEmail(user.getEmail());
        u.setAppMetadata(user.getAppMetadata());
        u.setUserMetadata(user.getUserMetadata());

        Request<User> request = mgmt.getManagementAPI().users().update(user.getId(), u);
        return (User) executeQuery(request, true);
    }

    public Auth0UserDto deleteUser(String uid) throws Auth0Exception {
        Request request = mgmt.getManagementAPI().users().delete(uid);
        return (Auth0UserDto) executeQuery(request, false);
    }

    private Object executeQuery(Request request, Boolean hasReturn) throws Auth0Exception {
        if (hasReturn) {
            return request.execute();
        }
        else {
            request.execute();
            return new Auth0UserDto();
        }
    }

    private User setDefaults(User user) {
        Map<String, Object> userMeta = user.getUserMetadata();
        if (userMeta == null) {
            userMeta = new HashMap<>();
        }

        if (!userMeta.containsKey("channel")) {
            userMeta.put("channel", UUID.randomUUID().toString());
        }
        if (!userMeta.containsKey("type")) {
            userMeta.put("type", "user");
        }
        user.setUserMetadata(userMeta);

        return user;
    }
}
