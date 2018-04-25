package ehb.finalwork.manager.dao;

import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dto.RethinkDataItemDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.error.CustomNotFoundWebSocketException;
import ehb.finalwork.manager.error.TooManyReturnValuesException;
import ehb.finalwork.manager.error.TooManyReturnValuesWebSocketException;
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

    @Override
    public DataItem getByItemId(String id) throws TooManyReturnValuesException, CustomNotFoundException {
        Cursor<DataItem> cursor =r.db("manager")
                                  .table("dataItem")
                                  .filter(row -> row.g("itemId").match(id))
                                  .run(connectionFactory.createConnection(), DataItem.class);

        if (cursor.bufferedSize() == 1) {
            return cursor.toList().get(0);
        }
        else if (cursor.bufferedSize() > 1) {
            throw new TooManyReturnValuesException("Too Many Values");
        }

        throw new CustomNotFoundException("Not Found");
    }
}
