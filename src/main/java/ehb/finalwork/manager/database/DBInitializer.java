package ehb.finalwork.manager.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import ehb.finalwork.manager.service.RoomChangeListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBInitializer implements InitializingBean
{
    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @Autowired
    private RoomChangeListener roomChangeListener;

    private static final RethinkDB r = RethinkDB.r;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        this.createDb();
        roomChangeListener.pushChangesToWebSocket();
    }

    private void createDb() {
        Connection con = connectionFactory.createConnection();
        // Get databases
        List<String> dbList = r.dbList().run(con);
        if (!dbList.contains("manager")) {
            r.dbCreate("manager").run(con);
        }
        // Get tables of Database
        List<String> tables = r.db("manager").tableList().run(con);
        if (!tables.contains("room")) {
            r.db("manager").tableCreate("room").run(con);
            r.db("manager").table("room").run(con);
        }
    }
}
