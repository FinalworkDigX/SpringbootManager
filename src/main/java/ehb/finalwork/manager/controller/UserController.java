package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.dto.RethinkUserDto;
import ehb.finalwork.manager.model.Room;
import ehb.finalwork.manager.model.User;
import ehb.finalwork.manager.service.RoomService;
import ehb.finalwork.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping()
//    public List<User> getUsers() {
//        return userService.getUsers();
//    }

    @PostMapping()
    public User create(@RequestBody RethinkUserDto userDto) {
        return userService.create(userDto);
    }

//    @DeleteMapping("/{rid}")
//    public void deleteRoom(@PathVariable String rid) {
//        roomService.deleteRoom(rid);
//    }


}
