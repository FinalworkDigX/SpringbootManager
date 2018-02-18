package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.dto.RethinkDtoTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableScheduling
public class ChangeListener {

    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private Cursor<RethinkDataLogDto> cursor;

    private RethinkDtoTemplate model;

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @Autowired
    private SimpMessagingTemplate webSocket;

    public void setModel(RethinkDtoTemplate model) {
        this.model = model;
    }

    @Async
    @Scheduled(cron = "0 */10 * * * *")
    public void startCursorScheduler() {

        log.warn("NEW SCHEDULED CRON JOB - " + model.getTableName());

        if (cursor != null) {
            cursor.close();
        }

        pushChangesToWebSocket();
    }

    private void pushChangesToWebSocket() {

        cursor = r.db("manager").table(model.getTableName()).changes()
                .getField("new_val")
                .run(connectionFactory.createConnection(), model.getClass());

        while (cursor.hasNext()) {
            try {
                RethinkDtoTemplate dl = cursor.next();
                log.info("New " + model.getTableName() + ": {}", dl.getId());
                webSocket.convertAndSend("/topic/" + dl.getTableName(), dl);
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

    }
}