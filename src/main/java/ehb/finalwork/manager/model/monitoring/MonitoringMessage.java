package ehb.finalwork.manager.model.monitoring;

import ehb.finalwork.manager.model.RethinkDBHashable;

import java.util.HashMap;

public class MonitoringMessage implements RethinkDBHashable{
    private CPU cpu;
    private Memory memory;

    public MonitoringMessage(CPU cpu, Memory memory) {
        this.cpu = cpu;
        this.memory = memory;
    }


    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("cpu", this.cpu);
        hashMap.put("memory", this.memory);

        return hashMap;
    }
}
