package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.model.Beacon;

import java.util.List;

public interface BeaconDao {
    public List<Beacon> getAll();
    public Beacon getById(String id);
    public Beacon create(RethinkBeaconDto beaconDto);
    public Beacon update(Beacon beacon);
    public void delete(String id);
}