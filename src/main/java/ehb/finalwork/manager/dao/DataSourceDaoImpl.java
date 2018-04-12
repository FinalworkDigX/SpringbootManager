package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkDataSourceDto;
import ehb.finalwork.manager.model.DataSource;


public class DataSourceDaoImpl extends BaseDaoImpl<DataSource, RethinkDataSourceDto> implements DataSourceDao {

    DataSourceDaoImpl(DataSource entity) {
        super(entity);
    }
}
