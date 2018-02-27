package ehb.finalwork.manager.model;

public class Information {
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
}
