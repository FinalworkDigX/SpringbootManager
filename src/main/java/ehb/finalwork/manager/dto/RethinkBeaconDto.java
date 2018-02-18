package ehb.finalwork.manager.dto;

import com.sun.javafx.geom.Vec3d;
import ehb.finalwork.manager.model.Beacon;

public class RethinkBeaconDto extends RethinkDtoTemplate {
    private String room_id;
    private String name;
    private String description;
    private Float calibrationFactor;
    private Vec3d location;

    public RethinkBeaconDto() {
    }

    public RethinkBeaconDto(String room_id, String name, String description, Float calibrationFactor, Vec3d location) {
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

    public Float getCalibrationFactor() {
        return calibrationFactor;
    }

    public void setCalibrationFactor(Float calibrationFactor) {
        this.calibrationFactor = calibrationFactor;
    }

    public Vec3d getLocation() {
        return location;
    }

    public void setLocation(Vec3d location) {
        this.location = location;
    }

    @Override
    public String getTableName() {
        return "beacon";
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
