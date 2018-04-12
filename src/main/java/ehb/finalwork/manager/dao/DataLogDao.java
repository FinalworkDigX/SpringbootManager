package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;

import java.util.List;

public interface DataLogDao extends BaseDao<DataLog, RethinkDataLogDto> {
    List<DataLog> getByItemId(String id);
}
