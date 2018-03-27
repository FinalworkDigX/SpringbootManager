package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dao.DataItemDao;
import ehb.finalwork.manager.dto.RethinkDataItemDto;
import ehb.finalwork.manager.model.DataItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataItemService {

    private final DataItemDao dataItemDao;

    private DataItemService(DataItemDao dataItemDao) {
        this.dataItemDao = dataItemDao;
    }

    public List<DataItem> getAll() {
        return dataItemDao.getAll();
    }

    public List<DataItem> getByRoomId(String rid) {
        return dataItemDao.getByRoomId(rid);
    }

    public DataItem getById(String id) {
        return dataItemDao.getById(id);
    }

    public DataItem create(RethinkDataItemDto dataItemDto) throws Exception  {
        return dataItemDao.create(dataItemDto);
    }
}
