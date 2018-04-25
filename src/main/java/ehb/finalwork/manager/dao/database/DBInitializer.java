package ehb.finalwork.manager.dao.database;

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
        if (connectionFactory.isTestAccount()) {
            log.info("Connected with test account");
            return;
        }

        this.createDb();

        roomChangeListener.startCursorScheduler();
        dataLogChangeListener.startCursorScheduler();
    }

    private void createDb() {
        Connection con = connectionFactory.createConnection();

        // Verify / Create database
        List<String> dbList = r.dbList().run(con);
        if (!dbList.contains("manager")) {
            r.dbCreate("manager").run(con);
        }

        // Verify / Create tables
        this.initDatabase("manager", con);
    }

    private void initDatabase(String database, Connection con) {

        List<String> tables = r.db(database).tableList().run(con);
        if (!tables.contains("user")) {
            r.db(database).tableCreate("user").run(con);
        }
        if (!tables.contains("room")) {
            r.db(database).tableCreate("room").run(con);
        }
        if (!tables.contains("beacon")) {
            r.db(database).tableCreate("beacon").run(con);
        }
        if (!tables.contains("dataItem")) {
            r.db(database).tableCreate("dataItem").run(con);
            r.db(database).table("dataItem").indexCreate("itemId").run(con);
        }
        if (!tables.contains("dataLog")) {
            r.db(database).tableCreate("dataLog").run(con);
            r.db(database).table("dataLog").indexCreate("itemId").run(con);
        }
        if (!tables.contains("dataSource")) {
            r.db(database).tableCreate("dataSource").run(con);
        }
        /* Verify / Create tables for each object? // 1 table with all info? */
    }
}
