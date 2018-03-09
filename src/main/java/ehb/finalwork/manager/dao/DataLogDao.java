package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;

import java.util.List;

public interface DataLogDao {
    public List<DataLog> getAll();
    public DataLog getById(String id);
    public List<DataLog> getByItemId(String id);
    public DataLog create(RethinkDataLogDto dataLogDto);
    public DataLog update(DataLog dataLog);
    public void delete(String id);
}
