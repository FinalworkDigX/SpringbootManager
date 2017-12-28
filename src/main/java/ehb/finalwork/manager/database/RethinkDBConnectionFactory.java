package ehb.finalwork.manager.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class RethinkDBConnectionFactory {
    private String host;

    public RethinkDBConnectionFactory(String host)
    {
        this.host = host;
    }

    public Connection createConnection()
    {
        return RethinkDB.r.connection().hostname(this.host).connect();
    }
}
