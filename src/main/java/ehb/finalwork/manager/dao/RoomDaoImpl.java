package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.model.Room;

public class RoomDaoImpl extends BaseDaoImpl<Room, RethinkRoomDto> implements RoomDao {

    RoomDaoImpl(Room entity) {
        super(entity);
    }
}
