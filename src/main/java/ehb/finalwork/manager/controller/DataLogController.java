package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.error.TooManyReturnValuesException;
import ehb.finalwork.manager.model.DataLog;
import ehb.finalwork.manager.service.DataLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TooManyListenersException;

@RestController
@RequestMapping("/v1/dataLog")
public class DataLogController {

    @Autowired
    private DataLogService dataLogService;

    @GetMapping()
    public List<DataLog> getAll() {
        return dataLogService.getAll();
    }

    @GetMapping("/byId/{dlid}")
    public DataLog getById(@PathVariable String dlid) throws CustomNotFoundException {
        return dataLogService.getById(dlid);
    }

    @GetMapping("/byItemId/{iid}")
    public List<DataLog> getByItemId(@PathVariable String iid) {
        return dataLogService.getByItemId(iid);
    }

    @PostMapping()
    public DataLog create(@RequestBody RethinkDataLogDto dataLogDto) throws Exception {
        return dataLogService.create(dataLogDto);
    }
}
