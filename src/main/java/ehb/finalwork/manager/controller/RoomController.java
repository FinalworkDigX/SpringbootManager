package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.model.Room;
import ehb.finalwork.manager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<Room> getRooms() {
        return roomService.getAll();
    }

    @PostMapping()
//    public ResponseEntity<Room> createRoom(@RequestBody RethinkRoomDto roomDto) {
    public Room createRoom(@RequestBody RethinkRoomDto roomDto) {

//        Room return_val = roomService.createRoom(roomDto);
//        return new ResponseEntity<Room>(return_val, HttpStatus.CREATED);

        return roomService.create(roomDto);
    }

    @DeleteMapping("/{rid}")
    public void deleteRoom(@PathVariable String rid) {
        roomService.delete(rid);
    }
}
