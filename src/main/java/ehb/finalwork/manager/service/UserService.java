package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkUserDto;
import ehb.finalwork.manager.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    RethinkDBConnectionFactory connectionFactory;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User create(RethinkUserDto userDto) {

        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        User user = r.db("manager").table("user").insert(userDto)
                .optArg("return_changes", true)
                .getField("changes").nth(0)
                .getField("new_val")
                .run(connectionFactory.createConnection(), User.class);

        log.info("Insert {}", user);
        return user;
    }
}
