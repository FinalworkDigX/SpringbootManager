package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.error.TooManyReturnValuesException;
import ehb.finalwork.manager.error.TooManyReturnValuesWebSocketException;
import ehb.finalwork.manager.model.Beacon;

import java.util.List;

public interface BeaconDao {
    List<Beacon> getAll();
    Beacon getById(String id);
    Beacon getByMajorMinor(String major, String minor, String privateChannel) throws Exception;
    Beacon create(RethinkBeaconDto beaconDto);
    Beacon update(Beacon beacon);
    void delete(String id);
}
