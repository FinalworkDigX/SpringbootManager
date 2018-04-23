package ehb.finalwork.manager.dto;

import ehb.finalwork.manager.model.RethinkDBHashable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InformationConversionDto implements RethinkDBHashable {
    private String name;
    private Long index;

    public InformationConversionDto() {
    }

    public InformationConversionDto(String name, Long index) {
        this.name = name;
        this.index = index;
    }

    public InformationConversionDto(HashMap<String, Object> dataLogData) {
        this.name = (String) dataLogData.get("name");
        this.index = (Long) dataLogData.get("index");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", this.name);
        hashMap.put("index", this.index);

        return hashMap;
    }

    public static ArrayList<HashMap<String, Object>> listToHashMap(List<InformationConversionDto> informationList) {

        ArrayList<HashMap<String, Object>> returnList = new ArrayList<HashMap<String, Object>>();
        for (InformationConversionDto info: informationList) {
            returnList.add(info.toHashMap());
        }

        return returnList;
    }
}
