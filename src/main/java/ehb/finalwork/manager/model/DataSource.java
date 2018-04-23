package ehb.finalwork.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSource extends ModelTemplate {
    private String url;
    private List<DataDestination> destinations;

    public DataSource() {
        this.destinations = new ArrayList<>();
    }

    public DataSource(String id, String url, List<DataDestination> destinations) {
        this.id = id;
        this.url = url;
        this.destinations = destinations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<DataDestination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DataDestination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public String getTableName() {
        return "dataSource";
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", this.id);
        hashMap.put("url", this.url);

        if (this.destinations != null) {
            hashMap.put("destinations", DataDestination.listToHashMap(this.destinations));
        }
        else {
            hashMap.put("destinations", null);
        }

        return hashMap;
    }
}
