package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkDataSourceDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.DataSource;
import ehb.finalwork.manager.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dataSource")
public class DataSourceController {

    @Autowired
    DataSourceService dataSourceService;

    @GetMapping()
    public List<DataSource> getAll() {
        return dataSourceService.getAll();
    }

    @GetMapping("/byId/{id}")
    public DataSource getById(@PathVariable String id) throws CustomNotFoundException {
        return dataSourceService.getById(id);
    }

    @PostMapping()
    public DataSource create(@RequestBody RethinkDataSourceDto dataSourceDto) throws Exception {
        return dataSourceService.create(dataSourceDto);
    }

    @PutMapping()
    public DataSource update(@RequestBody DataSource dataSource) {
        return  dataSourceService.update(dataSource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws CustomNotFoundException {
        dataSourceService.delete(id);
    }
}
