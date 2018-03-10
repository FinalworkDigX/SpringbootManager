package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ehb.finalwork.manager.model.Information;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RethinkDataLogDto {
    @JsonProperty("item_id")
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

    @JsonProperty("item_id")
    public String getItemId() {
        return itemId;
    }

    @JsonProperty("item_id")
    public void setItemId(String itemId) {
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
}
