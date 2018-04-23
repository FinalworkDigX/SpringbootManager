package ehb.finalwork.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataLog extends ModelTemplate {
    private String itemId;
    private List<Information> information;
    private Long timestamp;

    public DataLog() {
        information = new ArrayList<>();
    }

    public DataLog(String id, String itemId, List<Information> information, Long timestamp) {
        this.id = id;
        this.itemId = itemId;
        this.information = information;
        this.timestamp = timestamp;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<Information> getInformation() {
        return information;
    }

    public void setInformation(List<Information> information) {
        this.information = information;
    }

    public void addInformation(Information information) {
        this.information.add(information);
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", this.id);
        hashMap.put("itemId", this.itemId);
        hashMap.put("information", this.information);
        hashMap.put("timestamp", this.timestamp);

        if (this.information != null) {
            hashMap.put("information", Information.listToHashMap(this.information));
        }
        else {
            hashMap.put("information", null);
        }

        return hashMap;
    }

    @Override
    public String getTableName() {
        return "dataLog";
    }
}
