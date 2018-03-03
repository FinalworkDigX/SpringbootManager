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
    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    @Autowired
    RethinkDBConnectionFactory connectionFactory;

    public List<DataLog> getDataLogs() {

        return r.db("manager").table("dataLog").orderBy().optArg("index", r.desc("id")).orderBy("id").run(connectionFactory.createConnection(), DataLog.class);
    }

    public DataLog getDataLog(String id) {

        return r.db("manager").table("dataLog").get(id).run(connectionFactory.createConnection(), DataLog.class);
    }

    public List<DataLog> getDataLogByItem(String id) {

        return r.db("manager").table("dataLog").filter(row -> row.g("item_id").eq(id)).run(connectionFactory.createConnection(), DataLog.class);
    }

    public RethinkDataLogDto createDataLog(RethinkDataLogDto dataLogDto) {

        dataLogDto.setTimestamp(Instant.now().getEpochSecond());
        Object run = r.db("manager").table("dataLog").insert(dataLogDto).run(connectionFactory.createConnection());

        log.info("Insert {}", run);
        //TODO: Return 'DataLog'
        return dataLogDto;
    }
}
