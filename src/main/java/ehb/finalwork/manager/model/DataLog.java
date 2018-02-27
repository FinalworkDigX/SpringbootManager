package ehb.finalwork.manager.model;

import java.lang.reflect.Array;
import java.util.List;

public class DataLog extends ModelTemplate{
    private String item_id;
    private List<Information> information;
    private Long timestamp;

    public DataLog() {
    }

    public DataLog(String id, String item_id, List<Information> information, Long timestamp) {
        this.id = id;
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

    public List<Information> getInformation() {
        return information;
    }

    public void setInformation(List<Information> information) {
        this.information = information;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String getTableName() {
        return "dataLog";
    }
}
