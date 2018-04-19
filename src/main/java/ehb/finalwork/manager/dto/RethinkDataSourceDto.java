package ehb.finalwork.manager.dto;

import ehb.finalwork.manager.model.DataDestination;
import ehb.finalwork.manager.model.RethinkDBHashable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RethinkDataSourceDto implements RethinkDBHashable {
    private String url;
    private List<DataDestination> destinations;

    public RethinkDataSourceDto() {
        this.destinations = new ArrayList<>();
    }

    public RethinkDataSourceDto(String url, List<DataDestination> destinations) {
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
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
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
