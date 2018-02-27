package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.model.Beacon;
import ehb.finalwork.manager.service.BeaconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BeaconController {

    @Autowired
    BeaconService beaconService;

    @MessageMapping("/beacon/calibrate")
    @SendTo("/topic/beacon/calibrate")
    public Beacon calibrate(Beacon beacon) {

        return beaconService.calibrate(beacon);
    }

    @MessageMapping("/beacon/create")
    @SendTo("/topic/beacon/create")
    public RethinkBeaconDto create(RethinkBeaconDto beaconDto) {

        return beaconService.createBeacon(beaconDto);
    }

}
