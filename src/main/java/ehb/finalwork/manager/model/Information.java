package ehb.finalwork.manager.model;

import ehb.finalwork.manager.dto.InformationConversionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Information implements RethinkDBHashable {
    private String name;
    private String data;
    private Long index;

    public Information() {
    }

    public Information(String name, String data, Long index) {
        this.name = name;
        this.data = data;
        this.index = index;
    }

    public Information(InformationConversionDto informationConversionDto) {
        this.name = informationConversionDto.getName();
        this.index = informationConversionDto.getIndex();
    }

    public Information(HashMap<String, Object> hm) {
        this.name = (String) hm.get("name");
        this.index = (Long) hm.get("index");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("name", this.name);
        hashMap.put("data", this.data);
        hashMap.put("index", this.index);

        return hashMap;
    }

    public static ArrayList<HashMap<String, Object>> listToHashMap(List<Information> informationList) {

        ArrayList<HashMap<String, Object>> returnList = new ArrayList<HashMap<String, Object>>();
        for (Information info: informationList) {
            returnList.add(info.toHashMap());
        }

        return returnList;
    }
}
