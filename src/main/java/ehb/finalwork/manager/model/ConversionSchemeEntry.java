package ehb.finalwork.manager.model;

import ehb.finalwork.manager.dto.InformationConversionDto;

import java.util.HashMap;

public class ConversionSchemeEntry implements RethinkDBHashable {
    private String incomingDataKey;
    private Object dataLogData;

    public ConversionSchemeEntry() {
    }

    public ConversionSchemeEntry(String incomingDataKey, Object dataLogData) {
        this.incomingDataKey = incomingDataKey;
        this.dataLogData = dataLogData;
        this.convertDataLogData();
    }

    public ConversionSchemeEntry(HashMap<String, Object> entry) {
        this.incomingDataKey = (String) entry.get("incomingDataKey");
        this.dataLogData = (Object) entry.get("dataLogData");
        this.convertDataLogData();
    }

    private void convertDataLogData() {
        if (this.dataLogData instanceof String) {
            this.dataLogData = (String) dataLogData;
        }
        else if (this.dataLogData instanceof HashMap) {
            this.dataLogData = new InformationConversionDto((HashMap<String, Object>)dataLogData);
        }
        else {
            this.dataLogData = dataLogData;
        }
    }


    public String getIncomingDataKey() {
        return incomingDataKey;
    }

    public void setIncomingDataKey(String incomingDataKey) {
        this.incomingDataKey = incomingDataKey;
    }

    public Object getDataLogData() {
        return dataLogData;
    }

    public void setDataLogData(Object dataLogData) {
        this.dataLogData = dataLogData;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("incomingDataKey", this.incomingDataKey);
        if (dataLogData instanceof String) {
            hashMap.put("dataLogData", this.dataLogData);
        }
        else if (dataLogData instanceof InformationConversionDto) {
            InformationConversionDto infoConversionDto = (InformationConversionDto) dataLogData;
            hashMap.put("dataLogData", infoConversionDto.toHashMap());
        }

        return hashMap;
    }
}
