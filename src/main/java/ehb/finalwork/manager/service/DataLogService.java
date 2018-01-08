package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class DataLogService {

    private static final RethinkDB r = RethinkDB.r;
    protected final Logger log = LoggerFactory.getLogger(DataLogService.class);

    @Autowired
    RethinkDBConnectionFactory connectionFactory;

    public List<RethinkDataLogDto> getDataLogs() {

        List<RethinkDataLogDto> dataLogList = r.db("manager").table("data_log").orderBy().optArg("index", r.desc("id")).orderBy("id").run(connectionFactory.createConnection(), RethinkDataLogDto.class);

        return dataLogList;
    }

    public RethinkDataLogDto getDataLog(String id) {

        RethinkDataLogDto dataLogDto = r.db("manager").table("data_log").get(id).run(connectionFactory.createConnection(), RethinkDataLogDto.class);

        return dataLogDto;
    }

    public List<RethinkDataLogDto> getDataLogByItem(String id) {

        List<RethinkDataLogDto> dataLogList = r.db("manager").table("data_log").filter(row -> row.g("item_id").eq(id)).run(connectionFactory.createConnection(), RethinkDataLogDto.class);

        return dataLogList;
    }

    public DataLog createDataLog(DataLog dl) {

        dl.setTimeStamp(Instant.now().getEpochSecond());
        Object run = r.db("manager").table("data_log").insert(dl).run(connectionFactory.createConnection());

        log.info("Insert {}", run);
        return dl;
    }
}
