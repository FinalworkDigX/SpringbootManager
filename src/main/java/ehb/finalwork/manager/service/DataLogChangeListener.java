package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DataLogChangeListener {
    private final Logger log = LoggerFactory.getLogger(DataLogChangeListener.class);
    private RethinkDB r = RethinkDB.r;

    @Autowired
    RethinkDBConnectionFactory connectionFactory;

    @Autowired
    SimpMessagingTemplate webSocket;

    @Async
    public void pushChangesToWebSocket() {
        Cursor<RethinkDataLogDto> cursor = r.db("manager").table("data_log").changes()
                .getField("new_val")
                .run(connectionFactory.createConnection(), RethinkDataLogDto.class);

        while (cursor.hasNext()) {

            try {
                RethinkDataLogDto r = cursor.next();
                log.info("New Room: {}", r.getId());
                webSocket.convertAndSend("/topic/dataLog", r);
            }
            catch (NullPointerException e){
                // TODO: 29-12-17 item delete?
                log.info("Item deleted// ChangeListener cursor == null");
            }
        }
    }
}
