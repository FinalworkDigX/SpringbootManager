package ehb.finalwork.manager.dao;

import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.dao.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.RethinkReturnObject;
import ehb.finalwork.manager.model.Room;
import ehb.finalwork.manager.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private static final RethinkDB r = RethinkDB.r;
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @Override
    public List<Room> getAll() {
        return r.db("manager").table("room")
                .orderBy().optArg("index", r.desc("id"))
                .limit(20)
                .orderBy("id")
                .run(connectionFactory.createConnection(), Room.class);
    }

    @Override
    public Room getById(String id) throws CustomNotFoundException{
        Room rm = r.db("manager")
                .table("room")
                .get(id)
                .run(connectionFactory.createConnection(), Room.class);

        if (rm != null) {
            return rm;
        }
        throw new CustomNotFoundException("Room with id: '" + id + "' not found.");
    }

    @Override
    public Room create(RethinkRoomDto roomDto) {

        RethinkReturnObject returnObject = r.db("manager")
                .table("room")
                .insert(roomDto.toHashMap())
                .optArg("return_changes", true)
                .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        Room room = new Room();
        if (returnObject.getInserted() != 0) {
            room = (Room) returnObject.getFirstNewVal(room);
        }

        log.info("Room Insert: {}", returnObject.toString());
        return room;
    }

    @Override
    public Room update(Room room) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(String id) {
        Object run = r.db("manager").table("room").get(id).delete().optArg("return_changes", true).run(connectionFactory.createConnection());
        log.info("Delete {}", run);
        log.info("Delete id {}", id);
    }
}
