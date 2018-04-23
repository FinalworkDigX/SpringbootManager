package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.model.Beacon;

public interface BeaconDao extends BaseDao<Beacon, RethinkBeaconDto> {
    Beacon getByMajorMinor(String major, String minor, String privateChannel) throws Exception;
}
