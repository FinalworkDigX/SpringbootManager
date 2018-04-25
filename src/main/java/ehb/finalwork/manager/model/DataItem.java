package ehb.finalwork.manager.model;

import java.util.HashMap;

public class DataItem extends ModelTemplate {
    private String itemId;
    private String name;
    private Vector3 location;
    private String roomId;

    public DataItem() { }

    public DataItem(String itemId, String id, String name, Vector3 location, String roomId) {
        this.itemId = itemId;
        this.id = id;
        this.name = name;
        this.location = location;
        this.roomId = roomId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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
    public String getTableName() {
        return "dataItem";
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", this.id);
        hashMap.put("itemId", this.itemId);
        hashMap.put("roomId", this.roomId);
        hashMap.put("name", this.name);

        if (this.location != null) {
            hashMap.put("location", this.location.toHashMap());
        }
        else {
            hashMap.put("location", new Vector3().toHashMap());
        }

        return hashMap;
    }
}
