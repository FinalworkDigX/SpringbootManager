package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.model.Beacon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeaconService {
    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    public List<Beacon> getBeacons() {
        return r.db("manager").table("beacon").getAll().run(connectionFactory.createConnection(), Beacon.class);
    }

    public Beacon calibrate(Beacon beaconDto) {
        log.warn(beaconDto.getName());

        return r.db("manager")
                .table("beacon")
                .get(beaconDto.getId())
                .update(beaconDto)
                .optArg("return_changes", true)
                .getField("changes").nth(0)
                .getField("new_val")
                .run(connectionFactory.createConnection(), Beacon.class);
    }

    public RethinkBeaconDto createBeacon(RethinkBeaconDto beaconDto) {

        Object run = r.db("manager").table("beacon").insert(beaconDto).run(connectionFactory.createConnection());

        log.info("Insert {}", run);
        return beaconDto;
    }
}
