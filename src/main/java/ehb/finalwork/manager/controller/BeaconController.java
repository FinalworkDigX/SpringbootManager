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

    @MessageMapping("/calibrate/beacons")
    @SendTo("/topic/calibrate/beacons")
    public RethinkBeaconDto calibrate(RethinkBeaconDto beaconDto) {

        return beaconService.calibrate(beaconDto);
    }

}
