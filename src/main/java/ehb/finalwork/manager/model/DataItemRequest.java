package ehb.finalwork.manager.model;

import java.util.HashMap;

public class DataItemRequest  extends ModelTemplate {
    private String beaconId;
    private String dataItemName;
    private String requester;

    public DataItemRequest() {
    }

    public DataItemRequest(String id, String beaconId, String dataItemName, String requester) {
        this.id = id;
        this.beaconId = beaconId;
        this.dataItemName = dataItemName;
        this.requester = requester;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public String getDataItemName() {
        return dataItemName;
    }

    public void setDataItemName(String dataItemName) {
        this.dataItemName = dataItemName;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    @Override
    public String getTableName() {
        return "dataItemRequest";
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", this.id);
        hashMap.put("beaconId", this.beaconId);
        hashMap.put("dataItemName", this.dataItemName);
        hashMap.put("requester", this.requester);

        return hashMap;
    }
}