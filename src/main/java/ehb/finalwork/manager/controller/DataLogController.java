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
    private DataLogService dataLogService;

    @GetMapping()
    public List<DataLog> getDataLogs() {
        return dataLogService.getAll();
    }

    @GetMapping("/byId/{dlid}")
    public DataLog getDataLogById(@PathVariable String dlid) {
        return dataLogService.getById(dlid);
    }

    @GetMapping("/byItemId/{iid}")
    public List<DataLog> getDataLogByItemId(@PathVariable String iid) {
        return dataLogService.getByItemId(iid);
    }

    @PostMapping()
    public DataLog createDataLog(@RequestBody RethinkDataLogDto dataLogDto) {
        return dataLogService.create(dataLogDto);
    }
}
