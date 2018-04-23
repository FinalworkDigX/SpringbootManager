package ehb.finalwork.manager.dao;

import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dto.RethinkDataItemDto;
import ehb.finalwork.manager.model.DataItem;

import java.util.List;

public class DataItemImpl extends BaseDaoImpl<DataItem, RethinkDataItemDto> implements DataItemDao {

    DataItemImpl(DataItem entity) {
        super(entity);
    }

    @Override
    public List<DataItem> getByRoomId(String rid) {
        Cursor<DataItem> cursor = r.db("manager")
                                  .table("dataItem")
                                  .filter(row -> row.g("roomId").match(rid))
                                  .run(connectionFactory.createConnection(), DataItem.class);

        return cursor.toList();
    }
}
