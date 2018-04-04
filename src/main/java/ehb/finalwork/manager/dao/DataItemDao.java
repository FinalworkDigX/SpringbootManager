package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkDataItemDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.Beacon;
import ehb.finalwork.manager.model.DataItem;

import java.util.List;

public interface DataItemDao {
    List<DataItem> getAll();
    List<DataItem> getByRoomId(String rid);
    DataItem getById(String id);
    DataItem create(RethinkDataItemDto dataItemDto) throws Exception;
    DataItem update(DataItem dataItem);
    void delete(String id) throws CustomNotFoundException;
}
