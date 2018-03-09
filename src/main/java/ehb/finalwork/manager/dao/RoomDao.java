package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.model.Room;

import java.util.List;

public interface RoomDao {
    public List<Room> getAll();
    public Room getById(String id);
    public Room create(RethinkRoomDto roomDto);
    public Room update(Room room);
    public void delete(String id);
}
