package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkDataConversionDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.DataConversion;
import ehb.finalwork.manager.service.DataConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dataConversion")
public class DataConversionController {

    @Autowired
    DataConversionService dataConversionService;

    @GetMapping()
    public List<DataConversion> getAll() {
        return dataConversionService.getAll();
    }

    @GetMapping("/byId/{id}")
    public DataConversion getById(@PathVariable String id) throws CustomNotFoundException {
        return dataConversionService.getById(id);
    }

    @PostMapping()
    public DataConversion create(@RequestBody RethinkDataConversionDto dataConversionDto) throws Exception {
        return dataConversionService.create(dataConversionDto);
    }

    @PutMapping()
    public DataConversion update(@RequestBody DataConversion dataConversion) {
        return dataConversionService.update(dataConversion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws CustomNotFoundException {
        dataConversionService.delete(id);
    }
}
