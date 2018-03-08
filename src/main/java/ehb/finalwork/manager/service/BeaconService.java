package ehb.finalwork.manager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.model.Beacon;
import ehb.finalwork.manager.model.RethinkChangesReturn;
import ehb.finalwork.manager.model.RethinkReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BeaconService {
    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(DataLogService.class);

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    public List<Beacon> getBeacons() {
        return r.db("manager").table("beacon").getAll().run(connectionFactory.createConnection(), Beacon.class);
    }

    public Beacon calibrate(Beacon beacon) {
        log.warn(beacon.getName());

        RethinkReturnObject p = r.db("manager")
                .table("beacon")
                .get(beacon.getId())
                .update(beacon)
                .optArg("return_changes", true)
                .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        if (p.getReplaced() != 0) {
            return (Beacon) p.asMapped(p.getChanges().get(0).getNew_val(), new Beacon());

            // WORKING  SOLUTION ==> GENERALIZE

//            ObjectMapper mapper = new ObjectMapper();
//            log.info("if value: {}",p.getChanges());
//            Map m = p.getChanges().get(0);
//            RethinkChangesReturn<Beacon> br = mapper.convertValue(m, RethinkChangesReturn.class);
//            Beacon b = mapper.convertValue(br.getNew_val(), Beacon.class);
//            log.info("if value: {}",br);
        }

        log.info("pom: {}", p);

        return beacon;
    }

    public RethinkBeaconDto createBeacon(RethinkBeaconDto beaconDto) {

        Object run = r.db("manager").table("beacon").insert(beaconDto).run(connectionFactory.createConnection());

        log.info("Insert {}", run);
        return beaconDto;
    }
}
