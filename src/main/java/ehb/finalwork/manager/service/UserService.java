package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    RethinkDBConnectionFactory connectionFactory;

    public RethinkUserDto create(RethinkUserDto userDto) {

        Object run = r.db("manager").table("user").insert(userDto).run(connectionFactory.createConnection());

        log.info("Insert {}", run);
        //TODO: return 'Room'
        return userDto;
    }
}
