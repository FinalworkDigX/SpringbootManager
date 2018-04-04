package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.Room;

import java.util.List;

public interface RoomDao {
    List<Room> getAll();
    Room getById(String id) throws CustomNotFoundException;
    Room create(RethinkRoomDto roomDto);
    Room update(Room room);
    void delete(String id) throws CustomNotFoundException;
}
