package ehb.finalwork.manager.dao;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dao.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkDataItemDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.DataItem;
import ehb.finalwork.manager.model.RethinkReturnObject;
import ehb.finalwork.manager.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataItemImpl implements DataItemDao {
    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @Override
    public List<DataItem> getAll() {
        Cursor<DataItem> cursor = r.db("manager")
                .table("dataItem")
                .run(connectionFactory.createConnection(), DataItem.class);

        return cursor.toList();
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
    public DataItem getById(String id) {
        return r.db("manager")
                .table("dataItem")
                .get(id)
                .run(connectionFactory.createConnection(), DataItem.class);
    }

    @Override
    public DataItem create(RethinkDataItemDto dataItemDto) throws Exception {
        RethinkReturnObject returnObject = r.db("manager")
                                            .table("dataItem")
                                            .insert(dataItemDto.toHashMap())
                                            .optArg("return_changes", true)
                                            .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        DataItem dataLog = new DataItem();
        if (returnObject.getInserted() != 0) {
            return (DataItem) returnObject.getFirstNewVal(dataLog);
        }

        log.error("DataItem exception NOT YET IMPLEMENTED {}", returnObject);
        // Create custom exception
        throw new Exception();
    }

    @Override
    public DataItem update(DataItem dataItem) {
        RethinkReturnObject returnObject = r.db("manager")
                                            .table("dataItem")
                                            .get(dataItem.getId())
                                            .update(dataItem.toHashMap())
                                            .optArg("return_changes", true)
                                            .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        if (returnObject.getReplaced() != 0) {
            dataItem = (DataItem) returnObject.getFirstNewVal(dataItem);
        }
        else {
            log.error("DataItemNotUpdated: {}", returnObject);
        }

        return dataItem;
    }

    @Override
    public void delete(String id) throws CustomNotFoundException {
        RethinkReturnObject returnObject = r.db("manager")
                                            .table("dataItem")
                                            .get(id)
                                            .delete().optArg("return_changes", true)
                                            .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        log.info("Delete {}", returnObject);
        log.info("Delete id {}", id);
        if (returnObject.getDeleted() == 0) {
            throw new CustomNotFoundException("DataItem with id: " + id + ": Not Found");
        }
    }
}
