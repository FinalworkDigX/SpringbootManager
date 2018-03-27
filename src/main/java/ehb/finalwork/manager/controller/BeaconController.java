package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.model.Beacon;
import ehb.finalwork.manager.service.BeaconService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BeaconController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BeaconService beaconService;

    @MessageMapping("/beacon/{privateChannel}/getByMajorMinor/{major}/{minor}")
    @SendTo("/topic/beacon/{privateChannel}/getByMajorMinor")
    public Beacon getByMajorMinor(@DestinationVariable String privateChannel, @DestinationVariable String major, @DestinationVariable String minor) throws Exception {
        return beaconService.getByMajorMinor(major, minor, privateChannel);
    }

    @MessageMapping("/beacon/{privateChannel}/calibrate")
    @SendTo("/topic/beacon/{privateChannel}/calibrate")
    public Beacon calibrate(@DestinationVariable String privateChannel, Beacon beacon) {
        log.info(privateChannel + " - CF: " + beacon.getCalibrationFactor());
        return beaconService.calibrate(beacon.getId(), beacon.getCalibrationFactor());
    }

    @MessageMapping("/beacon/create")
    @SendTo("/topic/beacon/create")
    public Beacon create(@RequestBody RethinkBeaconDto beaconDto) {
        return beaconService.create(beaconDto);
    }

    @MessageMapping("/beacon")
    @SendTo("/topic/beacon")
    public List<Beacon> getAll() {
        log.info("get beacons");
        List<Beacon> lb = beaconService.getAll();
        log.info("{}", lb);
        return lb;
    }

    @MessageMapping("/beacon/generate")
    @SendTo("/topic/beacon")
    public List<Beacon> generate() {
        RethinkBeaconDto b1 = new RethinkBeaconDto("test_room", "beacon_1_1", "Lokaal a201 pos.1", 1L, 1L, 61);
        RethinkBeaconDto b2 = new RethinkBeaconDto("test_room", "beacon_1_2", "Lokaal a201 pos.2", 1L, 2L, 61);
        RethinkBeaconDto b3 = new RethinkBeaconDto("test_room", "beacon_1_3", "Lokaal a201 pos.3", 1L, 3L, 61);
        RethinkBeaconDto b4 = new RethinkBeaconDto("test_room", "beacon_1_3", "Lokaal a201 pos.3", 1L, 4L, 61);
        RethinkBeaconDto b5 = new RethinkBeaconDto("test_room", "beacon_1_3", "Lokaal a201 pos.3", 1L, 5L, 61);

        beaconService.create(b1);
        beaconService.create(b2);
        beaconService.create(b3);
        beaconService.create(b4);
        beaconService.create(b5);

        return beaconService.getAll();
    }
}
