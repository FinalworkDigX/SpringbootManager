package ehb.finalwork.manager.service;

import ehb.finalwork.manager.model.monitoring.CPU;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class MachineInformationService {

    public CPU getCpuLoadARMv7() {
        CPU myCPU = new CPU();

        myCPU.setCurrentClockSpeed(this.executeCommand("cat /sys/devices/system/cpu/cpufreq/policy0/scaling_cur_freq"));
        myCPU.setMaxClockSpeed(this.executeCommand("cat /sys/devices/system/cpu/cpufreq/policy0/cpuinfo_max_freq"));

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
