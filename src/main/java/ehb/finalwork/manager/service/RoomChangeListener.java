package ehb.finalwork.manager.service;

import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RoomChangeListener extends BaseChangeListener {

    @Async
    public void pushChangesToWebSocket() {

        log.warn("NEW DATA-LOG CURSOR");
        Cursor<RethinkRoomDto> cursor = r.db("manager").table("room").changes().getField("new_val").run(connectionFactory.createConnection(), RethinkRoomDto.class);

        while (cursor.hasNext()) {
            try {
                RethinkRoomDto room = cursor.next();
                log.info("New Room: {}", room.getName());
                webSocket.convertAndSend("/topic/room", room);
            }
            catch (Exception e) {
                log.error("===================================================================================================================");
                log.error("EXCEPTION: ", e);
                log.error("===================================================================================================================");

                // On error close change-feed & create new one
                cursor.close();
                this.pushChangesToWebSocket();
            }
            finally {
                log.info("----------------------------------------------------------------------------------------------------------------");
            }
        }
    }
}
