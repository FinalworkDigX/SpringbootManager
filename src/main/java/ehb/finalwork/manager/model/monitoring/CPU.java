package ehb.finalwork.manager.model.monitoring;

import ehb.finalwork.manager.model.RethinkDBHashable;
import org.jutils.jhardware.model.ProcessorInfo;

import java.util.HashMap;
import java.util.Map;

public class CPU implements RethinkDBHashable {
    private String name;
    private String maxClockSpeed;
    private String currentClockSpeed;
    private String temperature;
    private String loadPercentage;

    public CPU() {
    }

    public CPU(ProcessorInfo processorInfo) {
        Map<String, String> fullInfo = processorInfo.getFullInfo();

        // Seem to be Hardware specific.. (intel i7 2600)
        this.name = processorInfo.getModelName();
        this.maxClockSpeed = fullInfo.get("MaxClockSpeed");
        this.currentClockSpeed = fullInfo.get("CurrentClockSpeed");
        this.temperature = fullInfo.get("temperature");
        this.loadPercentage = fullInfo.get("LoadPercentage");
    }

    public CPU(String name, String maxClockSpeed, String currentClockSpeed, String temperature, String loadPercentage) {
        this.name = name;
        this.maxClockSpeed = maxClockSpeed;
        this.currentClockSpeed = currentClockSpeed;
        this.temperature = temperature;
        this.loadPercentage = loadPercentage;
    }

    public String getName() {
        return name;
    }

    public String getMaxClockSpeed() {
        return maxClockSpeed;
    }

    public String getCurrentClockSpeed() {
        return currentClockSpeed;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getLoadPercentage() {
        return loadPercentage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxClockSpeed(String maxClockSpeed) {
        this.maxClockSpeed = maxClockSpeed;
    }

    public void setCurrentClockSpeed(String currentClockSpeed) {
        this.currentClockSpeed = currentClockSpeed;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setLoadPercentage(String loadPercentage) {
        this.loadPercentage = loadPercentage;
    }

    public String calcLoad() {
        return String.format("%.2f", Double.parseDouble(currentClockSpeed) / Double.parseDouble(maxClockSpeed) * 100);
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", this.name);
        hashMap.put("currentClockSpeed", this.currentClockSpeed);
        hashMap.put("maxClockSpeed", this.maxClockSpeed);
        hashMap.put("loadPercentage", this.loadPercentage);
        hashMap.put("temperature", this.temperature);

        return hashMap;
    }
}
