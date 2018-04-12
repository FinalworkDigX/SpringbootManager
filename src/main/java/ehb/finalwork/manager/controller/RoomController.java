package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.dto.RoomForARDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.Beacon;
import ehb.finalwork.manager.model.DataItem;
import ehb.finalwork.manager.model.Room;
import ehb.finalwork.manager.service.DataItemService;
import ehb.finalwork.manager.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoomService roomService;

    @Autowired
    private DataItemService dataItemService;

    // ===================== //
    //      Web Sockets
    // ===================== //
    @MessageMapping("/room/{privateChannel}/{roomId}")
    @SendTo("/topic/room/{privateChannel}")
    public RoomForARDto getForAR(@DestinationVariable String privateChannel, @DestinationVariable String roomId, RoomForARDto roomForARDto) throws Exception {
        log.info("in room for ar");
        roomForARDto.setRoomInfo(roomService.getById(roomId));
        roomForARDto.setItemList(dataItemService.getByRoomId(roomId));

        log.info("{}", roomForARDto);
        return roomForARDto;
    }

    // ===================== //
    //      REST api
    // ===================== //
    @GetMapping()
    public List<Room> getAll() {
        return roomService.getAll();
    }

    @GetMapping("/byId/{rid}")
    public Room getById(@PathVariable String rid) throws Exception {
        return roomService.getById(rid);
    }

    @PostMapping()
    public Room create(@RequestBody RethinkRoomDto roomDto) throws Exception {
        return roomService.create(roomDto);
    }

    @PutMapping()
    public Room update(@RequestBody Room room) {
        return roomService.update(room);
    }

    @DeleteMapping("/{rid}")
    public void delete(@PathVariable String rid) throws CustomNotFoundException {
        roomService.delete(rid);
    }
}
