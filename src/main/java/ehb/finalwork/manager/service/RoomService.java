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

    public List<Room> getAll() {
        return roomDao.getAll();
    }

    public Room getById(String id) {
        return roomDao.getById(id);
    }

    public Room create(RethinkRoomDto roomDto) {
        return roomDao.create(roomDto);
    }

    public void delete(String id) {
        roomDao.delete(id);
    }
}
