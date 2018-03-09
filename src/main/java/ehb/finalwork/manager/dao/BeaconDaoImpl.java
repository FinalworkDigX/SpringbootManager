package ehb.finalwork.manager.dao;

import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.dao.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.model.Beacon;
import ehb.finalwork.manager.model.RethinkReturnObject;
import ehb.finalwork.manager.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class BeaconDaoImpl implements BeaconDao {

    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @Override
    public List<Beacon> getAll() {
        return r.db("manager")
                .table("beacon")
                .getAll()
                .run(connectionFactory.createConnection(), Beacon.class);
    }

    @Override
    public Beacon getBeaconById(String id) {
        throw new NotImplementedException();
    }

    @Override
    public Beacon create(RethinkBeaconDto beaconDto) {
        RethinkReturnObject returnObject = r.db("manager")
                .table("beacon")
                .insert(beaconDto)
                .optArg("return_changes", true)
                .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        Beacon beacon = new Beacon();
        if (returnObject.getInserted() != 0) {
            beacon = (Beacon) returnObject.getFirstNewVal(beacon);
        }

        log.info("Beacon Insert: {}", returnObject.toString());
        return beacon;
    }

    @Override
    public Beacon update(Beacon beacon) {
        RethinkReturnObject returnObject = r.db("manager")
                .table("beacon")
                .get(beacon.getId())
                .update(beacon)
                .optArg("return_changes", true)
                .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        if (returnObject.getReplaced() != 0) {
            beacon = (Beacon) returnObject.getFirstNewVal(beacon);
        }

        log.info("Beacon calibrated: {}", returnObject.toString());
        return beacon;
    }

    @Override
    public void delete(String id) {
        throw new NotImplementedException();
    }
}
