package ehb.finalwork.manager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataSource extends ModelTemplate {
    private String url;
    private List<String> channels;

    public DataSource() {
        this.channels = new ArrayList<>();
    }

    public DataSource(String id, String url, List<String> channels) {
        this.id = id;
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
    public String getTableName() {
        return "dataSource";
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        return null;
    }
}
