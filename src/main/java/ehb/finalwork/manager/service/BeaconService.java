package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dao.BeaconDao;
import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.model.Beacon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeaconService {
    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    @Autowired
    private BeaconDao beaconDao;

    public List<Beacon> getAll() {
        return beaconDao.getAll();
    }

    public Beacon calibrate(Beacon beacon) {
        return beaconDao.updateBeacon(beacon);
    }

    public Beacon create(RethinkBeaconDto beaconDto) {
        return beaconDao.create(beaconDto);
    }
}
