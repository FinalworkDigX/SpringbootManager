package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;
import ehb.finalwork.manager.service.DataLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dataLog")
public class DataLogController {

    @Autowired
    DataLogService dataLogService;

    @GetMapping()
    public List<DataLog> getDataLogs() {
        return dataLogService.getDataLogs();
    }

    @GetMapping("/byId/{dlid}")
    public DataLog getDataLogById(@PathVariable String dlid) {
        return dataLogService.getDataLog(dlid);
    }

    @GetMapping("/byItemId/{iid}")
    public List<DataLog> getDataLogByItemId(@PathVariable String iid) {
        return dataLogService.getDataLogByItem(iid);
    }

    @PostMapping()
    public RethinkDataLogDto createDataLog(@RequestBody RethinkDataLogDto dataLogDto) {
        return dataLogService.createDataLog(dataLogDto);
    }
}
