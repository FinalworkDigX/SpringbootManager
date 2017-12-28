package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.model.Room;
import ehb.finalwork.manager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Room postRoom(@RequestBody Room newRoom) {
        return roomService.createRoom(newRoom);
    }
}
