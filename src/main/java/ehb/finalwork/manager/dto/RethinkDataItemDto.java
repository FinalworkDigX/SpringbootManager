package ehb.finalwork.manager.dto;

import ehb.finalwork.manager.model.RethinkDBHashable;
import ehb.finalwork.manager.model.Vector3;

import java.util.HashMap;

public class RethinkDataItemDto implements RethinkDBHashable {
    private String name;
    private Vector3 location;
    private String roomId;

    public RethinkDataItemDto() { }

    public RethinkDataItemDto(String name, Vector3 location, String roomId) {
        this.name = name;
        this.location = location;
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector3 getLocation() {
        return location;
    }

    public void setLocation(Vector3 location) {
        this.location = location;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("roomId", this.roomId);

        if (this.location != null) {
            hashMap.put("location", this.location.toHashMap());
        }
        else {
            hashMap.put("location", new Vector3().toHashMap());
        }

        return hashMap;
    }
}
