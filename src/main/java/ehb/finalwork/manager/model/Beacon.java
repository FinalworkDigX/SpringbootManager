package ehb.finalwork.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.time.Instant;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Beacon extends ModelTemplate {
    private String roomId;
    private String name;
    private String description;
    private Long major;
    private Long minor;
    private Double calibrationFactor;
    private Vector3 location;
    private Long lastUpdated;

    public Beacon() {
        this.lastUpdated = Instant.now().getEpochSecond();
    }

    public Beacon(String id, String roomId, String name, String description, Long major, Long minor, Double calibrationFactor, Vector3 location) {
        this.id = id;
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.major = major;
        this.minor = minor;
        this.calibrationFactor = calibrationFactor;
        this.location = location;
        this.lastUpdated = Instant.now().getEpochSecond();
    }

    public String getRoomId() {
        return roomId;
    }

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

    public Long getMajor() {
        return major;
    }

    public void setMajor(Long major) {
        this.major = major;
    }

    public Long getMinor() {
        return minor;
    }

    public void setMinor(Long minor) {
        this.minor = minor;
    }

    public Double getCalibrationFactor() {
        return calibrationFactor;
    }

    public void setCalibrationFactor(Double calibrationFactor) {
        this.calibrationFactor = calibrationFactor;
    }

    public Vector3 getLocation() {
        return location;
    }

    public void setLocation(Vector3 location) {
        this.location = location;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated() {
        this.lastUpdated = Instant.now().getEpochSecond();
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTableName() {
        return "beacon";
    }

    @Override
    public HashMap<String, Object> toHashMap() {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", this.id);
        hashMap.put("roomId", this.roomId);
        hashMap.put("name", this.name);
        hashMap.put("description", this.description);
        hashMap.put("major", this.major);
        hashMap.put("minor", this.minor);
        hashMap.put("calibrationFactor", this.calibrationFactor);
        if (this.location != null) {
            hashMap.put("location", this.location.toHashMap());
        }
        else {
            hashMap.put("location", null);
        }
        hashMap.put("lastUpdated", this.lastUpdated);

        return hashMap;
    }
}
