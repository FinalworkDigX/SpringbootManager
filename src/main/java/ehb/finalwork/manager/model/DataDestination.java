package ehb.finalwork.manager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataDestination implements RethinkDBHashable {
    private String destination;
    private HashMap<String, Object> conversionScheme;

    public DataDestination() {
        conversionScheme = new HashMap<>();
    }

    public DataDestination(String destination, HashMap<String, Object> conversion) {
        this.destination = destination;
        this.conversionScheme = conversion;
    }

    // Issues with ReQL driver
    public DataDestination(HashMap<String, Object> destinationHash) {
        this.destination = (String) destinationHash.get("destination");
        this.conversionScheme = (HashMap<String, Object>) destinationHash.get("conversionScheme");
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public HashMap<String, Object> getConversionScheme() {
        return conversionScheme;
    }

    public void setConversionScheme(HashMap<String, Object> conversionScheme) {
        this.conversionScheme = conversionScheme;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("destination", this.destination);
        hashMap.put("conversionScheme", this.conversionScheme);

        return hashMap;
    }

    public static ArrayList<HashMap<String, Object>> listToHashMap(List<DataDestination> destinationList) {

        ArrayList<HashMap<String, Object>> returnList = new ArrayList<>();
        for (DataDestination destination: destinationList) {
            returnList.add(destination.toHashMap());
        }

        return returnList;
    }
}
