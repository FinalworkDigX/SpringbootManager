package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dao.RoomDao;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.dto.RoomForARDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final Logger log = LoggerFactory.getLogger(RoomService.class);

    private RoomDao roomDao;
    private DataItemService dataItemService;

    private RoomService(RoomDao roomDao,
                        DataItemService dataItemService
    ) {
        this.roomDao = roomDao;
        this.dataItemService = dataItemService;
    }

    public List<Room> getAll() {
        return roomDao.getAll();
    }

    public Room getById(String id) throws Exception {
        return roomDao.getById(id);
    }

    public RoomForARDto getForAR(String roomId, RoomForARDto roomForARDto) throws Exception {
        roomForARDto.setRoomInfo(roomDao.getById(roomId));
        roomForARDto.setItemList(dataItemService.getByRoomId(roomId));

        return roomForARDto;
    }

    public Room create(RethinkRoomDto roomDto) throws Exception {
        return roomDao.create(roomDto);
    }

    public Room update(Room room) throws Exception{
        return roomDao.update(room);
    }

    public void delete(String id) throws CustomNotFoundException {
        roomDao.delete(id);
    }
}
