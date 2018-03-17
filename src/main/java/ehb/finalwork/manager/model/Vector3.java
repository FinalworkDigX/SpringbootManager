package ehb.finalwork.manager.model;

import java.util.HashMap;

public class Vector3 {
    public Double x;
    public Double y;
    public Double z;

    public Vector3() {
    }

    public Vector3(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public HashMap<String, Object> toHashMap() {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("x", this.x);
        hashMap.put("y", this.y);
        hashMap.put("z", this.z);

        return hashMap;
    }
}
