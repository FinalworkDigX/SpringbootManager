package ehb.finalwork.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.javafx.geom.Vec3d;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Beacon extends ModelTemplate {
    @JsonProperty("room_id")
    private String roomId;
    private String name;
    private String description;
    private Double calibrationFactor;
    private Vec3d location;

    public Beacon() {
    }

    public Beacon(String id, String roomId, String name, String description, Double calibrationFactor, Vec3d location) {
        this.id = id;
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.calibrationFactor = calibrationFactor;
        this.location = location;
    }

    @JsonProperty("room_id")
    public String getRoomId() {
        return roomId;
    }

    @JsonProperty("room_id")
    public void setRoomId(String roomId) {
        this.roomId = roomId;
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
