package ehb.finalwork.manager.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import ehb.finalwork.manager.service.ChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class DBInitializer implements InitializingBean {

    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @Autowired
    @Qualifier("RoomChangeListener")
    private ChangeListener roomChangeListener;

    @Autowired
    @Qualifier("DataLogChangeListener")
    private ChangeListener dataLogChangeListener;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.createDb();
        log.info("item added");

        roomChangeListener.startCursorScheduler();
        dataLogChangeListener.startCursorScheduler();
    }

    private void createDb() {
        log.warn("before");
        Connection con = connectionFactory.createConnection();
        log.warn("after");
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
        if (!tables.contains("dataLog")) {
            r.db("manager").tableCreate("dataLog").run(con);
            r.db("manager").table("dataLog").indexCreate("item_id").run(con);
        }
        /* Verify / Create tables for each object? // 1 table with all info? */
    }
}
