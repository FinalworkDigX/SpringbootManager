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
        return beaconService.getAll();
    }
}
