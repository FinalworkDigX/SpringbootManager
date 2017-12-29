package ehb.finalwork.manager.dto;

public class RethinkRoomDto {
    private String id;
    private String name;
    private String Description;
    private String location;

    public RethinkRoomDto() {
    }

    public RethinkRoomDto(String id, String name, String description, String location) {
        this.id = id;
        this.name = name;
        Description = description;
        this.location = location;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
