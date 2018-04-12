package ehb.finalwork.manager.dao;

import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;

import java.util.List;

public class DataLogDaoImpl extends BaseDaoImpl<DataLog, RethinkDataLogDto> implements DataLogDao {

    DataLogDaoImpl(DataLog entity) {
        super(entity);
    }

    @Override
    public List<DataLog> getByItemId(String id) {

        Cursor<DataLog> cursor = r.db("manager")
                .table("dataLog")
                .filter(row -> row.g("itemId").match(id))
                .run(connectionFactory.createConnection(), DataLog.class);

        return cursor.toList();
    }
}
