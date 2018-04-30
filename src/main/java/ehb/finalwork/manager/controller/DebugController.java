package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.dto.RethinkDataItemDto;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.error.TooManyReturnValuesException;
import ehb.finalwork.manager.model.*;
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

    @Autowired
    MgmtApi mgmt;

    // ===================== //
    //      Web Sockets
    // ===================== //
    @MessageMapping("/echo/{channel}")
    @SendTo("/topic/echo/{channel}")
    public HashMap<String, Object> echo(@RequestBody HashMap<String, Object> json) {
        return json;
    }


    // ===================== //
    //      REST api
    // ===================== //

    @GetMapping("workaround/mgmt")
    public void debugMgmt() {
        mgmt.resetManagementAPI();
    }

    @GetMapping("/generate")
    public List<HashMap<String, String>> generateAll() throws Exception {

        List<HashMap<String, String>> returnList = new ArrayList<>();

        // Sport Scenario generate
        returnList.add(this.generateSport());

        //Cafe Scenario Generate
        returnList.add(this.generateCafe());


        return returnList;
    }

    @GetMapping("/generate/sport")
    public HashMap<String, String> generateSport() throws Exception {
        HashMap<String, String> returnMap = new HashMap<>();
        Vector3 originV = new Vector3(0.0, 0.0, 0.0);

        // Room
        RethinkRoomDto roomDto = new RethinkRoomDto("sport scenario", "Example scenario for IoT sport equipment", "Jupiter glv");
        Room room = roomService.create(roomDto);
        // Beacon
        RethinkBeaconDto beaconDto = new RethinkBeaconDto(room.getId(), "beacon_1_1", "Sport scenario beacon", 1L, 4L, 64L);
        this.deleteAndCreateBeacon(beaconDto);
        // DataItem
        RethinkDataItemDto dataItemDto = new RethinkDataItemDto("sport_scenario_item", "Sport Equipment", originV, room.getId());
        this.deleteAndCreateDataItem(dataItemDto);

        returnMap.put("sport_scenario id", dataItemDto.getItemId());
        return returnMap;
    }

    @GetMapping("/generate/cafe")
    public HashMap<String, String> generateCafe() throws Exception {
        HashMap<String, String> returnMap = new HashMap<>();
        Vector3 originV = new Vector3(0.0, 0.0, 0.0);

        // Room
        RethinkRoomDto roomDto = new RethinkRoomDto("cafe scenario", "Example scenario for smart cafe. Where bartenders and customer can see current stock", "Le corbeau");
        Room room = roomService.create(roomDto);
        // Beacon
        RethinkBeaconDto beaconDto = new RethinkBeaconDto(room.getId(), "beacon_1_5", "Cafe scenario beacon", 1L, 5L, 64L);
        this.deleteAndCreateBeacon(beaconDto);
        // DataItem
        RethinkDataItemDto dataItemDto = new RethinkDataItemDto("cafe_scenario_item", "Cooler 1", originV, room.getId());
        this.deleteAndCreateDataItem(dataItemDto);

        returnMap.put("cafe_scenario id", dataItemDto.getItemId());
        return returnMap;
    }



    private void deleteAndCreateBeacon(RethinkBeaconDto beaconDto) throws Exception {
        try {
            Beacon b = beaconService.getByMajorMinor(Long.toString(beaconDto.getMajor()), Long.toString(beaconDto.getMinor()), "autogenerate");
            beaconService.delete(b.getId());
        }
        catch (Exception ignored) { }
        beaconService.create(beaconDto);
    }

    private void deleteAndCreateDataItem(RethinkDataItemDto dataItemDto) throws Exception {
        try {
            DataItem di = dataItemService.getByItemId(dataItemDto.getItemId());
            dataItemService.delete(di.getId());
        }
        catch (Exception ignored) { }
        dataItemService.create(dataItemDto);
    }
}
