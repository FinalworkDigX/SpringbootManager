package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.model.Beacon;

import java.util.List;

public interface BeaconDao {
    public List<Beacon> getAllBeacons();
    public Beacon getBeaconById(String id);
    public Beacon createBeacon(RethinkBeaconDto beaconDto);
    public Beacon updateBeacon(Beacon beacon);
    public void deleteBeacon(String id);
}
