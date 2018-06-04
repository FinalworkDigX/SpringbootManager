package ehb.finalwork.manager.model.monitoring;

import ehb.finalwork.manager.model.RethinkDBHashable;
import org.jutils.jhardware.model.MemoryInfo;

import java.util.HashMap;

public class Memory implements RethinkDBHashable {
    private String totalMemory;
    private String freeMemory;
    private String availableMemory;

    public Memory() {
    }

    public Memory(MemoryInfo memoryInfo) {
        this.totalMemory = memoryInfo.getTotalMemory();
        this.freeMemory = memoryInfo.getFreeMemory();
        this.availableMemory = memoryInfo.getAvailableMemory();
    }

    public Memory(String totalMemory, String freeMemory, String availableMemory) {
        this.totalMemory = totalMemory;
        this.freeMemory = freeMemory;
        this.availableMemory = availableMemory;
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public String getFreeMemory() {
        return freeMemory;
    }

    public String getAvailableMemory() {
        return availableMemory;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("totalMemory", this.totalMemory);
        hashMap.put("freeMemory", this.freeMemory);
        hashMap.put("availableMemory", this.availableMemory);

        return hashMap;
    }
}
