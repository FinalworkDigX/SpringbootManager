package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ehb.finalwork.manager.model.RethinkDBHashable;
import ehb.finalwork.manager.model.Vector3;

import java.time.Instant;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RethinkBeaconDto implements RethinkDBHashable {
    private String roomId;
    private String name;
    private String description;
    private Long major;
    private Long minor;
    private Long calibrationFactor;
    private Long lastUpdated;


    public RethinkBeaconDto() {
        this.lastUpdated = Instant.now().getEpochSecond();
    }

    public RethinkBeaconDto(String roomId, String name, String description, Long major, Long minor, Long calibrationFactor) {
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.major = major;
        this.minor = minor;
        this.calibrationFactor = calibrationFactor;
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

    public Long getCalibrationFactor() {
        return calibrationFactor;
    }

    public void setCalibrationFactor(Long calibrationFactor) {
        this.calibrationFactor = calibrationFactor;
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
    public HashMap<String, Object> toHashMap() {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("roomId", this.roomId);
        hashMap.put("name", this.name);
        hashMap.put("major", this.major);
        hashMap.put("minor", this.minor);
        hashMap.put("description", this.description);
        hashMap.put("calibrationFactor", this.calibrationFactor);
        hashMap.put("lastUpdated", this.lastUpdated);

        return hashMap;
    }
}
