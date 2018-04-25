package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.dto.RethinkDataItemDto;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.model.Beacon;
import ehb.finalwork.manager.model.DataItem;
import ehb.finalwork.manager.model.Room;
import ehb.finalwork.manager.model.Vector3;
import ehb.finalwork.manager.service.BeaconService;
import ehb.finalwork.manager.service.DataItemService;
import ehb.finalwork.manager.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/debug")
public class DebugController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RoomService roomService;

    @Autowired
    BeaconService beaconService;

    @Autowired
    DataItemService dataItemService;

    // ===================== //
    //      Web Sockets
    // ===================== //
    @MessageMapping("/echo")
    @SendTo("/topic/echo")
    public HashMap<String, Object> echo(@RequestBody HashMap<String, Object> json) {
        log.info("in echo");
        return json;
    }


    // ===================== //
    //      REST api
    // ===================== //
    @GetMapping("/generate")
    public List<String> generateAll() throws Exception {

        Room r = this.generateRoom();
        List<DataItem> items = this.generateDataItems(r.getId());
        this.generateBeacons(r.getId());

        List<String> dataItemsIds = new ArrayList<>();
        for (DataItem di: items) {
            dataItemsIds.add(di.getId());
        }

        return dataItemsIds;
    }


    // $@GetMapping("/generate/room")
    private Room generateRoom() throws Exception{
        RethinkRoomDto r1 = new RethinkRoomDto("test_room", "room_desc_1", "Lokaal a201");

        return roomService.create(r1);

    }

    private List<DataItem> generateDataItems(String roomId) throws Exception {
        Vector3 v1 = new Vector3(0.0, 0.0, 0.0);
        RethinkDataItemDto di1 = new RethinkDataItemDto("test_id", "Screen 1", v1, roomId);

        dataItemService.create(di1);

        return dataItemService.getAll();
    }

    // @GetMapping("/generate/beacon")
    private List<Beacon> generateBeacons(String roomId) throws Exception {
        RethinkBeaconDto b1 = new RethinkBeaconDto(roomId, "beacon_1_1", "Lokaal a201 pos.1", 1L, 1L, 61L);
        RethinkBeaconDto b2 = new RethinkBeaconDto(roomId, "beacon_1_2", "Lokaal a201 pos.2", 1L, 2L, 61L);
        RethinkBeaconDto b3 = new RethinkBeaconDto(roomId, "beacon_1_3", "Lokaal a201 pos.3", 1L, 3L, 61L);
        RethinkBeaconDto b4 = new RethinkBeaconDto(roomId, "beacon_1_3", "Lokaal a201 pos.3", 1L, 4L, 61L);
        RethinkBeaconDto b5 = new RethinkBeaconDto(roomId, "beacon_1_3", "Lokaal a201 pos.3", 1L, 5L, 61L);

        beaconService.create(b1);
        beaconService.create(b2);
        beaconService.create(b3);
        beaconService.create(b4);
        beaconService.create(b5);

        return beaconService.getAll();
    }
}
