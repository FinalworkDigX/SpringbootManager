package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dao.BeaconDao;
import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.Beacon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeaconService {
    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    private BeaconDao beaconDao;

    public BeaconService(BeaconDao beaconDao) {
        this.beaconDao = beaconDao;
    }

    public List<Beacon> getAll() {
        return beaconDao.getAll();
    }

    public Beacon getByMajorMinor(String major, String minor, String privateChannel) throws Exception {
        return beaconDao.getByMajorMinor(major, minor, privateChannel);
    }

    public Beacon calibrate(String id, Long factor) throws CustomNotFoundException {
        Beacon b = beaconDao.getById(id);
        b.setCalibrationFactor(factor);
        b.setLastUpdated();
        return beaconDao.update(b);
    }

    public Beacon create(RethinkBeaconDto beaconDto) throws Exception {
        return beaconDao.create(beaconDto);
    }

    public Beacon update(Beacon beacon) {
        return beaconDao.update(beacon);
    }

    public void delete(String bid) throws CustomNotFoundException {
        beaconDao.delete(bid);
    }
}
