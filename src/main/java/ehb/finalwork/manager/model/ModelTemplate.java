package ehb.finalwork.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class ModelTemplate implements RethinkDBHashable {

    protected String id;

    @JsonIgnore
    public abstract String getTableName();


    public String getId() {
        return id;
    }

    // Abstract => changeListener doesn't populate id..
    public abstract void setId(String id);
}
