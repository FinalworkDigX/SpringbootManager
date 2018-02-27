package ehb.finalwork.manager.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public abstract class RethinkDtoTemplate {
    protected String id;

    public abstract String getTableName();

    public abstract String getId();

    public abstract void setId(String id);
}
