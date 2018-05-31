package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkDataItemRequestDto;
import ehb.finalwork.manager.model.DataItemRequest;

public class DataItemRequestDaoImpl extends BaseDaoImpl<DataItemRequest, RethinkDataItemRequestDto> implements DataItemRequestDao {

    DataItemRequestDaoImpl(DataItemRequest entity) {
        super(entity);
    }
}
