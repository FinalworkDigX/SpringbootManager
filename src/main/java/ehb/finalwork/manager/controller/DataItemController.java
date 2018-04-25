package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.RethinkDataItemDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.DataItem;
import ehb.finalwork.manager.service.DataItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dataItem")
public class DataItemController {

    @Autowired
    private DataItemService dataItemService;

    @GetMapping()
    public List<DataItem> getAll() {
        return dataItemService.getAll();
    }

    @GetMapping("/byRoomId/{rid}")
    public List<DataItem> getByRoomId(@PathVariable String rid) {
        return dataItemService.getByRoomId(rid);
    }

    @GetMapping("/byId/{id}")
    public DataItem getById(@PathVariable String id) throws CustomNotFoundException {
        return dataItemService.getById(id);
    }

    @GetMapping("/byItemId/{id}")
    public DataItem getByItemId(@PathVariable String id) throws Exception {
        return dataItemService.getByItemId(id);
    }

    @PostMapping
    public DataItem create(@RequestBody RethinkDataItemDto dataItemDto) throws Exception {
        return dataItemService.create(dataItemDto);
    }

    @PutMapping()
    public DataItem update(@RequestBody DataItem dataItem) {
        return dataItemService.update(dataItem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws CustomNotFoundException {
        dataItemService.delete(id);
    }
}
