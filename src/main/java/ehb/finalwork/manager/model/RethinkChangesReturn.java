package ehb.finalwork.manager.model;

import java.util.HashMap;

public class RethinkChangesReturn {

    private int deleted;

    private HashMap new_val;
    private HashMap old_val;

    public RethinkChangesReturn() {
    }

    public RethinkChangesReturn(HashMap new_val, HashMap old_val) {
        this.new_val = new_val;
        this.old_val = old_val;
    }

    public HashMap getNew_val() {
        return new_val;
    }

    public void setNew_val(HashMap new_val) {
        this.new_val = new_val;
    }

    public HashMap getOld_val() {
        return old_val;
    }

    public void setOld_val(HashMap old_val) {
        this.old_val = old_val;
    }
}
