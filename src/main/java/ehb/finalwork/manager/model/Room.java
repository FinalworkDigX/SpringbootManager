package ehb.finalwork.manager.model;

import java.util.HashMap;

public class Room extends ModelTemplate{
    private String name;
    private String description;
    private String location;

    public Room() {
    }

    public Room(String id, String name, String description, String location) {
        this.id = id;
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
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTableName() {
        return "room";
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", this.id);
        hashMap.put("name", this.name);
        hashMap.put("description", this.description);
        hashMap.put("location", this.location);

        return hashMap;
    }
}
