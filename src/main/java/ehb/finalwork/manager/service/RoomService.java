package ehb.finalwork.manager.service;

import com.rethinkdb.RethinkDB;
import ehb.finalwork.manager.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.model.Id;
import ehb.finalwork.manager.model.Room;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;

@Service
public class RoomService {

    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    private static final RethinkDB r = RethinkDB.r;
    protected final Logger log = LoggerFactory.getLogger(RoomService.class);

    public List<Room> getRooms() {

        List<Room> rooms = r.db("manager").table("room")
                .orderBy().optArg("index", r.desc("id"))
                .limit(20)
                .orderBy("id")
                .run(connectionFactory.createConnection(), Room.class);

        return rooms;
    }

    public Room createRoom(Room newRoom) {

        Object run = r.db("manager").table("room").insert(newRoom).run(connectionFactory.createConnection());

        log.info("Insert {}", run);
        return newRoom;
    }

    public void deleteRoom(Id id) {

        Object run = r.db("manager").table("room").get(id.getId()).delete().optArg("return_changes", true).run(connectionFactory.createConnection());

        log.info("Delete {}", run);
        log.info("Delete id {}", id.getId());
    }
}