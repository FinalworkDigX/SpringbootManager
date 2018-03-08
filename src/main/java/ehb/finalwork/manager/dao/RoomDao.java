package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.model.Room;

import java.util.List;

public interface RoomDao {
    public List<Room> getAllRooms();
    public Room getRoomById(String id);
    public Room createRoom(RethinkRoomDto roomDto);
    public Room updateRoom(Room room);
    public void deleteRoom(String id);
}
