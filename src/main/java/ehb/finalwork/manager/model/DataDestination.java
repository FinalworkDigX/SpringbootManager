package ehb.finalwork.manager.model;

import ehb.finalwork.manager.dto.InformationConversionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDestination implements RethinkDBHashable {
    private String destination;
    private List<ConversionSchemeEntry> conversionScheme;

    private Logger log = LoggerFactory.getLogger(getClass());

    public DataDestination() {
        conversionScheme = new ArrayList<>();
    }

    public DataDestination(String destination, List<ConversionSchemeEntry> conversion) {
        this.destination = destination;
        this.conversionScheme = conversion;
    }

    // Issues with ReQL driver
    public DataDestination(HashMap<String, Object> destinationHash) {
        this.destination = (String) destinationHash.get("destination");
        this.conversionScheme = new ArrayList<>();

        Object conversionSchemeObj = destinationHash.get("conversionScheme");
        if (conversionSchemeObj instanceof List) {
            if (((List) conversionSchemeObj).size() > 0) {
                Object testObj = ((List) conversionSchemeObj).get(0);

                if (testObj instanceof InformationConversionDto) {
                    this.conversionScheme = (List<ConversionSchemeEntry>) conversionSchemeObj;
                }
                if (testObj instanceof HashMap) {
                    List<HashMap<String, Object>> hmList = (List<HashMap<String, Object>>) conversionSchemeObj;

                    for (HashMap<String, Object> schemeEntry : hmList) {
                        this.conversionScheme.add(new ConversionSchemeEntry(schemeEntry));
                    }



//                    HashMap<String, Object> schemeHM = (HashMap<String, Object>) conversionSchemeObj;
//
//                    for (String incomingDataKey : schemeHM.keySet()) {
//                        this.conversionScheme.add(new ConversionSchemeEntry(incomingDataKey, schemeHM.get(incomingDataKey)));
//                    }
                }
            }
        }
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<ConversionSchemeEntry> getConversionScheme() {
        return conversionScheme;
    }

    public void setConversionScheme(List<ConversionSchemeEntry> conversionScheme) {
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
