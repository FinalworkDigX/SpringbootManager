package ehb.finalwork.manager.service;

import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataLogChangeListener extends BaseChangeListener {

    // TODO: 09/01/2018 SCHEDULER reset cursor every 30minutes -> stops to respond

    private Cursor<RethinkDataLogDto> cursor;

    @Async
    @Scheduled(cron = "*/15 * * * *")
    public void startCursorScheduler() {

        log.warn("NEW SCHEDULED CRON JOB");

        if (cursor != null) {
            cursor.close();
        }
        log.warn("1");
        pushChangesToWebSocket();
    }

    @Async
    public void pushChangesToWebSocket() {

        log.warn("2");
        cursor = r.db("manager").table("data_log").changes()
                .getField("new_val")
                .run(connectionFactory.createConnection(), RethinkDataLogDto.class);

        log.warn("3");
        while (cursor.hasNext()) {
            try {
                log.warn("DL Before change -cusrosr.next()");
                RethinkDataLogDto dl = cursor.next();
                log.info("New DataLog: {}", dl.getId());
                webSocket.convertAndSend("/topic/dataLog", dl);
            }
            catch (Exception e) {
                log.error("===================================================================================================================");
                log.error("DATA-LOG EXCEPTION: ", e);
                log.error("===================================================================================================================");

                // On error close change-feed & create new one
                startCursorScheduler();
            }
            finally {
                log.info("----------------------------------------------------------------------------------------------------------------");
            }
        }
        log.warn("4");

    }
}
