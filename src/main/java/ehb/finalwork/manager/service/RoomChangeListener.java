package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RoomChangeListener {
    protected final Logger log = LoggerFactory.getLogger(RoomChangeListener.class);
    private static final RethinkDB r = RethinkDB.r;

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @Autowired
    private SimpMessagingTemplate webSocket;

    @Async
    public void pushChangesToWebSocket() {
        Cursor<Room> cursor = r.db("manager").table("room").changes()
                .getField("new_val")
                .run(connectionFactory.createConnection(), Room.class);

        while (cursor.hasNext()) {

            Room r = cursor.next();
            log.info("New Room: {}", r.getName());
            webSocket.convertAndSend("/topic/room", r);
        }
    }
}
