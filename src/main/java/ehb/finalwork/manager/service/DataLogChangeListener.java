package ehb.finalwork.manager.service;

import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DataLogChangeListener extends BaseChangeListener {

    @Async
    public void pushChangesToWebSocket() {
        log.warn("NEW DATA-LOG CURSOR");
        Cursor<RethinkDataLogDto> cursor = r.db("manager").table("data_log").changes().getField("new_val").run(connectionFactory.createConnection(), RethinkDataLogDto.class);

        while (cursor.hasNext()) {
            try {
                RethinkDataLogDto dl = cursor.next();
                log.info("New DataLog: {}", dl.getId());
                webSocket.convertAndSend("/topic/dataLog", dl);
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
