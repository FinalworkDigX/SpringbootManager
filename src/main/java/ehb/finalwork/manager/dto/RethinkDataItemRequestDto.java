package ehb.finalwork.manager.dto;

import ehb.finalwork.manager.model.RethinkDBHashable;

import java.util.HashMap;

public class RethinkDataItemRequestDto implements RethinkDBHashable {
    private String beaconId;
    private String dataItemName;
    private String requester;

    public RethinkDataItemRequestDto() {
    }

    public RethinkDataItemRequestDto(String beaconId, String dataItemName, String requester) {
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
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("beaconId", this.beaconId);
        hashMap.put("dataItemName", this.dataItemName);
        hashMap.put("requester", this.requester);

        return hashMap;
    }
}
