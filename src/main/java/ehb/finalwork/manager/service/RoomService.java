package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dao.RoomDao;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RoomDao roomDao;

    public List<Room> getRooms() {
        return roomDao.getAllRooms();
    }

    public Room createRoom(RethinkRoomDto roomDto) {
        return roomDao.createRoom(roomDto);
    }

    public void deleteRoom(String id) {
        roomDao.deleteRoom(id);
    }
}
