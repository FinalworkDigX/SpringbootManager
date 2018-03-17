package ehb.finalwork.manager.dto;

import ehb.finalwork.manager.model.RethinkDBHashable;

import java.util.HashMap;

public class RethinkRoomDto implements RethinkDBHashable {
    private String name;
    private String description;
    private String location;

    public RethinkRoomDto() {
    }

    public RethinkRoomDto(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("name", this.name);
        hashMap.put("description", this.description);
        hashMap.put("location", this.location);

        return hashMap;
    }
}
