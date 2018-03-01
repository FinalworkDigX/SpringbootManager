package ehb.finalwork.manager.service;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.Request;
import com.sun.org.apache.xpath.internal.operations.Bool;
import ehb.finalwork.manager.dto.Auth0UserDto;
import ehb.finalwork.manager.model.MgmtAPIWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Auth0ManagementService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MgmtAPIWrapper mgmt;

    public User createUser(User user) {
        // Set defaults
        user.setConnection(mgmt.getConnection());

        Request<User> request = mgmt.users().create(user);
        return executeQuery(request, false);
    }

    public User updateUser(User user) {
        // Set defaults
        user.setConnection(mgmt.getConnection());

        Request<User> request = mgmt.users().update(user.getId(), user);
        return executeQuery(request, false);
    }

    public Auth0UserDto deleteUser(String uid) {
        Request request = mgmt.users().delete(uid);
        return executeQuery(request, true);
    }

    private Auth0UserDto executeQuery(Request request, Boolean emptyReturn) {
        try {
            if (emptyReturn) {
                request.execute();
                return new Auth0UserDto();
            }
            else {
                return (Auth0UserDto) request.execute();
            }
        }
        catch (Auth0Exception e) {
            log.error(e.getMessage());
            return new Auth0UserDto(e.getMessage().split(": ")[1]);
        }
    }
}
