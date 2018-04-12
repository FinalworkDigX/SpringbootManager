package ehb.finalwork.manager.dto;

import ehb.finalwork.manager.model.RethinkDBHashable;

import java.util.HashMap;

public class RethinkDataConversionDto implements RethinkDBHashable {
    private HashMap<String, Object> conversion;

    public RethinkDataConversionDto() {
        this.conversion = new HashMap<>();
    }

    public RethinkDataConversionDto(HashMap<String, Object> conversion) {
        this.conversion = conversion;
    }

    public HashMap<String, Object> getConversion() {
        return conversion;
    }

    public void setConversion(HashMap<String, Object> conversion) {
        this.conversion = conversion;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        return null;
    }
}
