package ehb.finalwork.manager.dao.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RethinkDBConnectionFactory {

    private final Logger log = LoggerFactory.getLogger(DBInitializer.class);
    private static RethinkDBConnectionFactory instance;

    private String host;
    private String username;
    private String password;
    private final boolean testing;

    private RethinkDBConnectionFactory(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.testing = password.equals("");
    }

    public static RethinkDBConnectionFactory getInstance(String host, String username, String password) {
        if (instance == null) {
            instance = new RethinkDBConnectionFactory(host, username, password);
        }
        return instance;
    }

    public boolean isTestAccount() {
        return this.testing;
    }

    public Connection createConnection() {
        //Automatically closes after 20seconds
        return RethinkDB.r.connection()
                .hostname(this.host)
                .user(this.username, this.password)
                .connect();
    }
}
