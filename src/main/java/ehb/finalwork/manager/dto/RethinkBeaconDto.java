package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ehb.finalwork.manager.model.RethinkDBHashable;
import ehb.finalwork.manager.model.Vector3;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RethinkBeaconDto implements RethinkDBHashable {
    @JsonProperty("room_id")
    private String roomId;
    private String name;
    private String description;
    @JsonProperty("calibration_factor")
    private Double calibrationFactor;
    private Vector3 location;

    public RethinkBeaconDto() {
    }

    public RethinkBeaconDto(String roomId, String name, String description, Double calibrationFactor, Vector3 location) {
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

    public Vector3 getLocation() {
        return location;
    }

    public void setLocation(Vector3 location) {
        this.location = location;
    }

    @Override
    public HashMap<String, Object> toHashMap() {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("room_id", this.roomId);
        hashMap.put("name", this.name);
        hashMap.put("description", this.description);
        hashMap.put("calibration_factor", this.calibrationFactor);
        if (this.location != null) {
            hashMap.put("location", this.location.toHashMap());
        }
        else {
            hashMap.put("location", null);
        }

        return hashMap;
    }
}
