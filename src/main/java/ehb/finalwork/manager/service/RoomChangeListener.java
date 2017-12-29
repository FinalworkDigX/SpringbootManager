package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkRoomDto;
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
        Cursor<RethinkRoomDto> cursor = r.db("manager").table("room").changes()
                .getField("new_val")
                .run(connectionFactory.createConnection(), RethinkRoomDto.class);

        while (cursor.hasNext()) {

            try {
                RethinkRoomDto r = cursor.next();
                log.info("New Room: {}", r.getName());
                webSocket.convertAndSend("/topic/room", r);
            }
            catch (NullPointerException e){
                // TODO: 29-12-17 item delete?
                log.info("Item deleted// ChangeListener cursor == null");
            }
        }
    }
}
