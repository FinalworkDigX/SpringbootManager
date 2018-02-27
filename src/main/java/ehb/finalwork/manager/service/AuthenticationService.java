package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.dto.RethinkUserDto;
import ehb.finalwork.manager.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    @Autowired
    RethinkDBConnectionFactory connectionFactory;

    public List<User> getDataLogs() {

        return r.db("manager").table("user").orderBy().optArg("index", r.desc("id")).orderBy("id").run(connectionFactory.createConnection(), User.class);
    }

}
