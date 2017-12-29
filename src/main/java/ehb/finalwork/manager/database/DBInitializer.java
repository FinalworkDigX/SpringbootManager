package ehb.finalwork.manager.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import ehb.finalwork.manager.service.DataLogChangeListener;
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

    @Autowired
    private DataLogChangeListener dataLogChangeListener;

    private static final RethinkDB r = RethinkDB.r;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        this.createDb();
        roomChangeListener.pushChangesToWebSocket();
        dataLogChangeListener.pushChangesToWebSocket();
    }

    private void createDb() {
        Connection con = connectionFactory.createConnection();
        // Verify / Create database
        List<String> dbList = r.dbList().run(con);
        if (!dbList.contains("manager")) {
            r.dbCreate("manager").run(con);
        }

        // Verify / Create tables
        List<String> tables = r.db("manager").tableList().run(con);
        if (!tables.contains("room")) {
            r.db("manager").tableCreate("room").run(con);
           // r.db("manager").table("room").run(con);
        }
        if (!tables.contains("beacon")) {
            r.db("manager").tableCreate("beacon").run(con);
            // r.db("manager").table("room").run(con);
        }
        if (!tables.contains("item")) {
            r.db("manager").tableCreate("item").run(con);
            // r.db("manager").table("room").run(con);
        }
        if (!tables.contains("data_log")) {
            r.db("manager").tableCreate("data_log").run(con);
             r.db("manager").table("data_log").indexCreate("item_id").run(con);
        }
        /* Verify / Create tables for each object? // 1 table with all info? */
    }
}
