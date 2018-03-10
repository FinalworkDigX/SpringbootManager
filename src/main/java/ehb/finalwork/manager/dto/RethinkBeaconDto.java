package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.javafx.geom.Vec3d;
import ehb.finalwork.manager.model.Beacon;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RethinkBeaconDto {
    @JsonProperty("room_id")
    private String roomId;
    private String name;
    private String description;
    @JsonProperty("calibration_factor")
    private Double calibrationFactor;
    private Vec3d location;

    public RethinkBeaconDto() {
    }

    public RethinkBeaconDto(String roomId, String name, String description, Double calibrationFactor, Vec3d location) {
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

    @JsonProperty("calibration_factor")
    public Double getCalibrationFactor() {
        return calibrationFactor;
    }

    @JsonProperty("calibration_factor")
    public void setCalibrationFactor(Double calibrationFactor) {
        this.calibrationFactor = calibrationFactor;
    }

    public Vec3d getLocation() {
        return location;
    }

    public void setLocation(Vec3d location) {
        this.location = location;
    }
}
