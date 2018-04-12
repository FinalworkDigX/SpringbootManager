package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dao.DataSourceDao;
import ehb.finalwork.manager.dto.RethinkDataSourceDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSourceService {
    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    private DataSourceDao dataSourceDao;

    public DataSourceService(DataSourceDao dataSourceDao) {
        this.dataSourceDao = dataSourceDao;
    }

    public List<DataSource> getAll() {
        return dataSourceDao.getAll();
    }

    public DataSource getById(String id) throws CustomNotFoundException {
        return dataSourceDao.getById(id);
    }

    public DataSource create(RethinkDataSourceDto dataSourceDto) throws Exception {
        return dataSourceDao.create(dataSourceDto);
    }

    public DataSource update(DataSource dataConversion) {
        return dataSourceDao.update(dataConversion);
    }

    public void delete(String id) throws CustomNotFoundException {
        dataSourceDao.delete(id);
    }
}
