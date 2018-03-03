package ehb.finalwork.manager.model;

public class Room extends ModelTemplate{
    private String name;
    private String Description;
    private String location;

    public Room() {
    }

    public Room(String id, String name, String description, String location) {
        this.id = id;
        this.name = name;
        Description = description;
        this.location = location;
    }

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

    @Override
    public String getTableName() {
        return "room";
    }
}
