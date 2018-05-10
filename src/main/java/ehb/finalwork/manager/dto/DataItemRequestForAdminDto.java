package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ehb.finalwork.manager.model.Beacon;
import ehb.finalwork.manager.model.DataItemRequest;
import ehb.finalwork.manager.model.Room;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataItemRequestForAdminDto extends DataItemRequest{
    private Beacon beacon;
    private Room room;


    public DataItemRequestForAdminDto() { }

    public DataItemRequestForAdminDto(String id, String beaconId, String dataItemName, Beacon beacon, Room room, String requester) {
        super(id, beaconId, dataItemName, requester);
        this.beacon = beacon;
        this.room = room;
    }

    public DataItemRequestForAdminDto(DataItemRequest request) {
        super(request.getId(), request.getBeaconId(), request.getDataItemName(), request.getRequester());
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
