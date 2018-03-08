package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;

import java.util.List;

public interface DataLogDao {
    public List<DataLog> getAllDataLogs();
    public DataLog getDataLogById(String id);
    public List<DataLog> getDataLogByItemId(String id);
    public DataLog createDataLog(RethinkDataLogDto dataLogDto);
    public DataLog updateDataLog(DataLog dataLog);
    public void deleteDataLog(String id);
}
