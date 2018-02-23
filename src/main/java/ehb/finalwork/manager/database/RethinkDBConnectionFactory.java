package ehb.finalwork.manager.database;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.InputStream;

public class RethinkDBConnectionFactory {


    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(DBInitializer.class);

    private String host;
    private String username;
    private String password;
    private Resource certfile;

    public RethinkDBConnectionFactory(String host, String username, String password, Resource certfile) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.certfile = certfile;
    }

    public Connection createConnection() {
        //Automatically closes after 20seconds

        try {
            InputStream cert = certfile.getInputStream();

            return RethinkDB.r.connection()
                    .hostname(this.host)
                    .user(username, password)
                    .port(2096)
                    .certFile(cert)
                    .connect();
        }
        catch (Exception e) {
            log.warn("fuck");
            log.warn(e.getMessage());
        }
        return null;
    }
}
