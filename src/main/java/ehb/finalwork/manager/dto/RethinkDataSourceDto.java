package ehb.finalwork.manager.dto;

import ehb.finalwork.manager.model.RethinkDBHashable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RethinkDataSourceDto implements RethinkDBHashable {
    private String url;
    private List<String> channels;

    public RethinkDataSourceDto() {
        this.channels = new ArrayList<>();
    }

    public RethinkDataSourceDto(String url, List<String> channels) {
        this.url = url;
        this.channels = channels;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        return null;
    }
}
