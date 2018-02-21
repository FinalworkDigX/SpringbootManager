package ehb.finalwork.manager.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class RethinkDBConnectionFactory {

    private String host;
    private String username;
    private String password;

    public RethinkDBConnectionFactory(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public Connection createConnection() {
        //Automatically closes after 20seconds
        return RethinkDB.r.connection()
                .hostname(this.host)
                .user(username, password)
                .connect();
    }
}
