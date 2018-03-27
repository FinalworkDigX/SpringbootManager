package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.dto.RoomForARDto;
import ehb.finalwork.manager.model.Beacon;
import ehb.finalwork.manager.model.Room;
import ehb.finalwork.manager.service.DataItemService;
import ehb.finalwork.manager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private DataItemService dataItemService;

    @GetMapping()
    public List<Room> getRooms() {
        return roomService.getAll();
    }

    @GetMapping("/byId/{rid}")
    public Room getRoomById(@PathVariable String rid) throws Exception {
        return roomService.getById(rid);
    }

    @PostMapping()
    public Room createRoom(@RequestBody RethinkRoomDto roomDto) {
        return roomService.create(roomDto);
    }

    @DeleteMapping("/{rid}")
    public void deleteRoom(@PathVariable String rid) {
        roomService.delete(rid);
    }

    @MessageMapping("/room/{privateChannel}/{roomId}")
    @SendTo("/topic/room/{privateChannel}")
    public RoomForARDto roomForAR(@DestinationVariable String privateChannel, @DestinationVariable String roomId, Beacon beacon) {

        return new RoomForARDto();
    }
}
