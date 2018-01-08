package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DataLogChangeListener extends BaseChangeListener{

    @Async
    public void pushChangesToWebSocket() {
        r = RethinkDB.r;

        Cursor<RethinkDataLogDto> cursor = r.db("manager").table("data_log").changes()
                .getField("new_val")
                .run(connectionFactory.createConnection(), RethinkDataLogDto.class);

        while (cursor.hasNext()) {

            try {
                log.warn("test-error 26");
                RethinkDataLogDto r = cursor.next();
                log.info("New DataLog: {}", r.getId());
                webSocket.convertAndSend("/topic/dataLog", r);
            }
            catch (NullPointerException e){
                // TODO: 29-12-17 item delete?
                log.info("Item deleted// ChangeListener cursor == null");
            }
        }
    }
}
