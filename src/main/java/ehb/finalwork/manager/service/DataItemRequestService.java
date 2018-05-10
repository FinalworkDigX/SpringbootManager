package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dao.DataItemRequestDao;
import ehb.finalwork.manager.dto.DataItemRequestForAdminDto;
import ehb.finalwork.manager.dto.RethinkDataItemRequestDto;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.DataItemRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataItemRequestService {

    private DataItemRequestDao dataItemRequestDao;
    private BeaconService beaconService;
    private RoomService roomService;


    public DataItemRequestService(
            DataItemRequestDao dataItemRequestDao,
            BeaconService beaconService,
            RoomService roomService
    ) {
        this.dataItemRequestDao = dataItemRequestDao;
        this.beaconService = beaconService;
        this.roomService = roomService;
    }

    public List<DataItemRequest> getAll() {
        return dataItemRequestDao.getAll();
    }

    public List<DataItemRequestForAdminDto> getForAdministration() {
        List<DataItemRequestForAdminDto> dataItemRequestForAdminDtoList = new ArrayList<>();
        List<DataItemRequest> dataItemRequestList = this.getAll();

        for (DataItemRequest request : dataItemRequestList) {
            DataItemRequestForAdminDto requestForAdminDto = new DataItemRequestForAdminDto(request);
            try {
                requestForAdminDto.setBeacon(beaconService.getById(request.getBeaconId()));
                // If there is a beacon, there should be a room linked to it..
                requestForAdminDto.setRoom(roomService.getById(requestForAdminDto.getBeacon().getRoomId()));
            } catch (Exception ignored) { }

            dataItemRequestForAdminDtoList.add(requestForAdminDto);
        }

        return dataItemRequestForAdminDtoList;
    }

    public DataItemRequest getById(String id) throws CustomNotFoundException {
        return dataItemRequestDao.getById(id);
    }

    public DataItemRequest create(RethinkDataItemRequestDto dataItemRequestDto) throws Exception {
        return dataItemRequestDao.create(dataItemRequestDto);
    }

    public DataItemRequest update(DataItemRequest dataItemRequest) {
        return dataItemRequestDao.update(dataItemRequest);
    }

    public void delete(String id) throws CustomNotFoundException {
        dataItemRequestDao.delete(id);
    }
}
