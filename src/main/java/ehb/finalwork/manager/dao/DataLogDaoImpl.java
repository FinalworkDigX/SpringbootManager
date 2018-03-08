package ehb.finalwork.manager.dao;

import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.dao.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;
import ehb.finalwork.manager.model.RethinkReturnObject;
import ehb.finalwork.manager.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class DataLogDaoImpl implements DataLogDao {

    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @Override
    public List<DataLog> getAllDataLogs() {
        return r.db("manager")
                .table("dataLog")
                .orderBy().optArg("index", r.desc("id"))
                .orderBy("id")
                .run(connectionFactory.createConnection(), DataLog.class);
    }

    @Override
    public DataLog getDataLogById(String id) {
        return r.db("manager")
                .table("dataLog")
                .get(id)
                .run(connectionFactory.createConnection(), DataLog.class);
    }

    @Override
    public List<DataLog> getDataLogByItemId(String id) {
        return r.db("manager")
                .table("dataLog")
                .filter(row -> row.g("item_id").eq(id))
                .run(connectionFactory.createConnection(), DataLog.class);
    }

    @Override
    public DataLog createDataLog(RethinkDataLogDto dataLogDto) {
        RethinkReturnObject returnObject = r.db("manager")
                .table("dataLog")
                .insert(dataLogDto)
                .optArg("return_changes", true)
                .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        DataLog dataLog = new DataLog();
        if (returnObject.getInserted() != 0) {
            dataLog = (DataLog) returnObject.getFirstNewVal(dataLog);
        }

        log.info("DataLog Insert: {}", returnObject.toString());
        return dataLog;
    }

    @Override
    public DataLog updateDataLog(DataLog dataLog) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteDataLog(String id) {
        throw new NotImplementedException();
    }
}
