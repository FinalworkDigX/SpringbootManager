package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RoomChangeListener extends BaseChangeListener{

    @Async
    public void pushChangesToWebSocket() {
        r = RethinkDB.r;

        Cursor<RethinkRoomDto> cursor = r.db("manager").table("room").changes()
                .getField("new_val")
                .run(connectionFactory.createConnection(), RethinkRoomDto.class);

        while (cursor.hasNext()) {

            try {
                log.warn("test-error 26");
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
