package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ehb.finalwork.manager.model.DataItem;
import ehb.finalwork.manager.model.Room;
import ehb.finalwork.manager.model.Vector3;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomForARDto {
    private Vector3 roomLocation;
    private Room roomInfo;
    private List<DataItem> itemList;

    public RoomForARDto() { }

    public RoomForARDto(Vector3 roomLocation, Room roomInfo, List<DataItem> itemList) {
        this.roomLocation = roomLocation;
        this.roomInfo = roomInfo;
        this.itemList = itemList;
    }

    public Vector3 getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(Vector3 roomLocation) {
        this.roomLocation = roomLocation;
    }

    public Room getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(Room roomInfo) {
        this.roomInfo = roomInfo;
    }

    public List<DataItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<DataItem> itemList) {
        this.itemList = itemList;
    }
}
