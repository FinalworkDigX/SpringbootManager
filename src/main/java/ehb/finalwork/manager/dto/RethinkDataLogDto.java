package ehb.finalwork.manager.dto;

public class RethinkDataLogDto {
    private String id;
    private String item_id;
    private String information;
    private Long timestamp;

    public RethinkDataLogDto() {
    }

    public RethinkDataLogDto(String id, String item_id, String information, Long timestamp) {
        this.id = id;
        this.item_id = item_id;
        this.information = information;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
