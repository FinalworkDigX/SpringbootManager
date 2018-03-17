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
import org.springframework.stereotype.Controller;

@Controller
public class BeaconController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BeaconService beaconService;

    @MessageMapping("/beacon/calibrate/{privateChannel}")
    @SendTo("/topic/beacon/calibrate/{privateChannel}")
    public Beacon calibrate(@DestinationVariable String privateChannel, Beacon beacon) {

        log.warn(privateChannel + " - CF: " + beacon.getCalibrationFactor());
        Beacon b = beaconService.calibrate(beacon.getId(), beacon.getCalibrationFactor());
        return b;
    }

    @MessageMapping("/beacon/create")
    @SendTo("/topic/beacon/create")
    public Beacon create(RethinkBeaconDto beaconDto) {

        return beaconService.create(beaconDto);
    }

}
