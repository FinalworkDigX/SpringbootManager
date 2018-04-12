package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
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
@RequestMapping("/v1/beacon")
public class BeaconController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BeaconService beaconService;

    // ===================== //
    //      Web Sockets
    // ===================== //
    @MessageMapping("/beacon/{privateChannel}/getByMajorMinor/{major}/{minor}")
    @SendTo("/topic/beacon/{privateChannel}/getByMajorMinor")
    public Beacon getByMajorMinorWS(@DestinationVariable String privateChannel, @DestinationVariable String major, @DestinationVariable String minor) throws Exception {
        return beaconService.getByMajorMinor(major, minor, privateChannel);
    }

    @MessageMapping("/beacon/{privateChannel}/calibrate")
    @SendTo("/topic/beacon/{privateChannel}/calibrate")
    public Beacon calibrateWS(@DestinationVariable String privateChannel, Beacon beacon) throws CustomNotFoundException {
        log.info(privateChannel + " - CF: " + beacon.getCalibrationFactor());
        return beaconService.calibrate(beacon.getId(), beacon.getCalibrationFactor());
    }

    @MessageMapping("/beacon/create")
    @SendTo("/topic/beacon/create")
    public Beacon createWS(@RequestBody RethinkBeaconDto beaconDto) throws Exception {
        return beaconService.create(beaconDto);
    }

    @MessageMapping("/beacon")
    @SendTo("/topic/beacon")
    public List<Beacon> getAllWS() {
        return beaconService.getAll();
    }


    // ===================== //
    //      REST api
    // ===================== //
    @GetMapping()
    public List<Beacon> getAll() {
        return beaconService.getAll();
    }

    @PostMapping()
    public Beacon create(@RequestBody RethinkBeaconDto beaconDto) throws Exception {
        return beaconService.create(beaconDto);
    }

    @PutMapping()
    public Beacon update(@RequestBody Beacon beacon) {
        return beaconService.update(beacon);
    }

    @DeleteMapping("/{bid}")
    public void delete(@PathVariable String bid) throws CustomNotFoundException {
        beaconService.delete(bid);
    }
}
