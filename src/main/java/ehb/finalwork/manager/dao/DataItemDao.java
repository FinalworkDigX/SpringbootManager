package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkDataItemDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.error.TooManyReturnValuesException;
import ehb.finalwork.manager.model.DataItem;

import java.util.List;

public interface DataItemDao extends BaseDao<DataItem, RethinkDataItemDto> {
    List<DataItem> getByRoomId(String rid);
    DataItem getByItemId(String id) throws TooManyReturnValuesException, CustomNotFoundException;
}
