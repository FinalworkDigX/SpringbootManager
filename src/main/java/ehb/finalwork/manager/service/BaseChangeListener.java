package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public abstract class BaseChangeListener {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected static RethinkDB r = RethinkDB.r;

    @Autowired
    protected RethinkDBConnectionFactory connectionFactory;

    @Autowired
    protected SimpMessagingTemplate webSocket;
}
