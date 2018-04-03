package ehb.finalwork.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class RethinkChangesReturn {

    private int deleted;

    @JsonProperty("new_val")
    private HashMap newVal;

    @JsonProperty("old_val")
    private HashMap oldVal;

    public RethinkChangesReturn() {
    }

    public RethinkChangesReturn(HashMap newVal, HashMap oldVal) {
        this.newVal = newVal;
        this.oldVal = oldVal;
    }

    @JsonProperty("new_val")
    public HashMap getNewVal() {
        return newVal;
    }

    @JsonProperty("new_val")
    public void setNewVal(HashMap newVal) {
        this.newVal = newVal;
    }

    @JsonProperty("old_val")
    public HashMap getOldVal() {
        return oldVal;
    }

    @JsonProperty("old_val")
    public void setOldVal(HashMap oldVal) {
        this.oldVal = oldVal;
    }
}
