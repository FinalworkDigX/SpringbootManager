package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;

import java.util.List;

public interface DataLogDao {
    List<DataLog> getAll();
    DataLog getById(String id);
    List<DataLog> getByItemId(String id);
    DataLog create(RethinkDataLogDto dataLogDto);
    DataLog update(DataLog dataLog);
    void delete(String id);
}
