package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ehb.finalwork.manager.model.Information;
import ehb.finalwork.manager.model.RethinkDBHashable;

import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RethinkDataLogDto implements RethinkDBHashable {
    private String itemId;
    private List<Information> information;
    private Long timestamp;

    public RethinkDataLogDto() {
    }

    public RethinkDataLogDto(String itemId, List<Information> information, Long timestamp) {
        this.itemId = itemId;
        this.information = information;
        this.timestamp = timestamp;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItem_id(String itemId) {
        this.itemId = itemId;
    }

    public List<Information> getInformation() {
        return information;
    }

    public void setInformation(List<Information> information) {
        this.information = information;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
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
}
