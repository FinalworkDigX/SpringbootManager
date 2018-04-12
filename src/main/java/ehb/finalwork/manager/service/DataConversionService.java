package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dao.DataConversionDao;
import ehb.finalwork.manager.dto.RethinkDataConversionDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.DataConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataConversionService {
    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    private DataConversionDao dataConversionDao;

    public DataConversionService(DataConversionDao dataConversionDao) {
        this.dataConversionDao = dataConversionDao;
    }

    public List<DataConversion> getAll() {
        return dataConversionDao.getAll();
    }

    public DataConversion getById(String id) throws CustomNotFoundException {
        return dataConversionDao.getById(id);
    }

    public DataConversion create(RethinkDataConversionDto dataConversionDto) throws Exception {
        return dataConversionDao.create(dataConversionDto);
    }

    public DataConversion update(DataConversion dataConversion) {
        return dataConversionDao.update(dataConversion);
    }

    public void delete(String id) throws CustomNotFoundException {
        dataConversionDao.delete(id);
    }
}
