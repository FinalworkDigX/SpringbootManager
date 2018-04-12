package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkDataConversionDto;
import ehb.finalwork.manager.model.DataConversion;

public class DataConversionDaoImpl extends BaseDaoImpl<DataConversion, RethinkDataConversionDto> implements DataConversionDao {

    DataConversionDaoImpl(DataConversion entity) {
        super(entity);
    }
}
