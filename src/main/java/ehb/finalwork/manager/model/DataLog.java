package ehb.finalwork.manager.model;

public class DataLog {
    private String item_id;
    private String information;
    private Long timestamp;

    public DataLog() {
    }

    public DataLog(String item_id, String information, Long timestamp) {
        this.item_id = item_id;
        this.information = information;
        this.timestamp = timestamp;
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

    public void setTimeStamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
