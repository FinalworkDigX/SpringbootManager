package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;
import ehb.finalwork.manager.service.DataLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dataLog")
public class DataLogController {

    @Autowired
    DataLogService dataLogService;

    @GetMapping()
    public List<RethinkDataLogDto> getDataLogs() {
        return dataLogService.getDataLogs();
    }

    @GetMapping("/byId/{dlid}")
    public RethinkDataLogDto getDataLogById(@PathVariable String dlid) {
        return dataLogService.getDataLog(dlid);
    }

    @GetMapping("/byItemId/{iid}")
    public  List<RethinkDataLogDto> getDataLogByItemId(@PathVariable String iid) {
        return dataLogService.getDataLogByItem(iid);
    }

    @PostMapping()
    public DataLog createDataLog(@RequestBody DataLog datalog) {
        return dataLogService.createDataLog(datalog);
    }
}
