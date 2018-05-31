package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.dto.DataItemRequestForAdminDto;
import ehb.finalwork.manager.dto.RethinkDataItemRequestDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.DataItemRequest;
import ehb.finalwork.manager.service.DataItemRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/request")
public class DataItemRequestController {

    @Autowired
    private DataItemRequestService dataItemRequestService;

    @GetMapping()
    public List<DataItemRequest> getAll() {
        return dataItemRequestService.getAll();
    }

    @GetMapping("/getForAdmin")
    public List<DataItemRequestForAdminDto> getForAdministration() {
        return dataItemRequestService.getForAdministration();
    }

    @GetMapping("/byId/{id}")
    public DataItemRequest getById(@PathVariable String id) throws CustomNotFoundException {
        return dataItemRequestService.getById(id);
    }

    @PostMapping()
    public DataItemRequest create(@RequestBody RethinkDataItemRequestDto dataItemRequestDto) throws Exception {
        return dataItemRequestService.create(dataItemRequestDto);
    }

    @PutMapping()
    public DataItemRequest update(@RequestBody DataItemRequest dataItemRequest) throws Exception {
        return dataItemRequestService.update(dataItemRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws CustomNotFoundException {
        dataItemRequestService.delete(id);
    }

}
