package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dao.DataLogDao;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class DataLogService {
    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    @Autowired
    private DataLogDao dataLogDao;

    public List<DataLog> getDataLogs() {
        return dataLogDao.getAllDataLogs();
    }

    public DataLog getDataLog(String id) {
        return dataLogDao.getDataLogById(id);
    }

    public List<DataLog> getDataLogByItem(String id) {
        return dataLogDao.getDataLogByItemId(id);
    }

    public DataLog createDataLog(RethinkDataLogDto dataLogDto) {
        dataLogDto.setTimestamp(Instant.now().getEpochSecond());
        return dataLogDao.createDataLog(dataLogDto);
    }
}
