package ehb.finalwork.manager.model;

import java.util.HashMap;

public interface RethinkDBHashable {
    HashMap<String, Object> toHashMap();
}
