package ehb.finalwork.manager.service;

import ehb.finalwork.manager.model.monitoring.CPU;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class MachineInformationService {

    public CPU getCpuLoadARMv7() {
        CPU myCPU = new CPU();

        Double current = Double.parseDouble(this.executeCommand("cat /sys/devices/system/cpu/cpufreq/policy0/scaling_cur_freq")) / 1000000;
        Double max = Double.parseDouble(this.executeCommand("cat /sys/devices/system/cpu/cpufreq/policy0/cpuinfo_max_freq")) / 1000000;

        myCPU.setCurrentClockSpeed(String.format("%.2f", current));
        myCPU.setMaxClockSpeed(String.format("%.2f", max));
        myCPU.setLoadPercentage(String.format("%.2f", current / max * 100));

        return myCPU;
    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
}
