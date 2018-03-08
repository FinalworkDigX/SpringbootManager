package ehb.finalwork.manager.model;

import com.sun.javafx.geom.Vec3d;

public class Beacon extends ModelTemplate{
    private String room_id;
    private String name;
    private String description;
    private Double calibrationFactor;
    private Vec3d location;

    public Beacon() {
    }

    public Beacon(String id, String room_id, String name, String description, Double calibrationFactor, Vec3d location) {
        this.id = id;
        this.room_id = room_id;
        this.name = name;
        this.description = description;
        this.calibrationFactor = calibrationFactor;
        this.location = location;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
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

    public Double getCalibrationFactor() {
        return calibrationFactor;
    }

    public void setCalibrationFactor(Double calibrationFactor) {
        this.calibrationFactor = calibrationFactor;
    }

    public Vec3d getLocation() {
        return location;
    }

    public void setLocation(Vec3d location) {
        this.location = location;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTableName() {
        return "beacon";
    }
}
