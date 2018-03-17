package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dao.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.model.ModelTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ChangeListener {

    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private Cursor<ModelTemplate> cursor;

    private ModelTemplate model;

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @Autowired
    private SimpMessagingTemplate webSocket;

    public void setModel(ModelTemplate model) {
        this.model = model;
    }

    @Async
    @Scheduled(cron = "0 */10 * * * *")
    public void startCursorScheduler() {

        if (this.model != null) {

            log.info("NEW SCHEDULED CRON JOB - " + this.model.getTableName());

            if (cursor != null) {
                cursor.close();
            }

            try {
                pushChangesToWebSocket();
            }
            catch (Exception e) {
                // No properties -> Connection time out / wrong credentials
                log.error(e.getMessage());
            }
        }
        else {
            log.warn("TODO: search origin of rogue instance");
        }
    }

    private void pushChangesToWebSocket() {

        cursor = r.db("manager").table(this.model.getTableName()).changes()
                .getField("new_val")
                .run(connectionFactory.createConnection(), this.model.getClass());

        while (cursor.hasNext()) {
            try {
                ModelTemplate dl = cursor.next();
                log.info("New " + this.model.getTableName() + ": {}", dl.getId());
                log.warn("pom");
                webSocket.convertAndSend("/topic/" + dl.getTableName(), dl);
            }
            catch (NullPointerException e) {
                log.error("delete? - " + e.getLocalizedMessage());
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
