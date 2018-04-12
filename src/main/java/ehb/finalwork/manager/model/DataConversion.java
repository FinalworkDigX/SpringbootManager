package ehb.finalwork.manager.model;

import java.util.HashMap;

public class DataConversion extends ModelTemplate {
    private HashMap<String, Object> conversion;

    public DataConversion() {
        conversion = new HashMap<>();
    }

    public DataConversion(String id, HashMap<String, Object> conversion) {
        this.id = id;
        this.conversion = conversion;
    }

    @Override
    public String getTableName() {
        return "dataConversion";
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        return null;
    }
}
