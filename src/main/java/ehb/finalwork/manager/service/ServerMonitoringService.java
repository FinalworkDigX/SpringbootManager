package ehb.finalwork.manager.service;

import ehb.finalwork.manager.model.monitoring.CPU;
import ehb.finalwork.manager.model.monitoring.Memory;
import ehb.finalwork.manager.model.monitoring.MonitoringMessage;
import org.jutils.jhardware.HardwareInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ServerMonitoringService {

    private final SimpMessagingTemplate webSocket;
    private final MachineInformationService machineInformationService;

    @Autowired
    public ServerMonitoringService(SimpMessagingTemplate webSocket, MachineInformationService machineInformationService) {
        this.webSocket = webSocket;
        this.machineInformationService = machineInformationService;
    }

    @Async
    @Scheduled(cron = "*/5 * * * * *")
    public void startMonitoring() {
        CPU myCpu = new CPU(HardwareInfo.getProcessorInfo());
        Memory myMem = new Memory(HardwareInfo.getMemoryInfo());

        // Fix specialy created for own server running an ARMv7 (TinkerBoard w/ TinkerOS)
        if (myCpu.getCurrentClockSpeed() == null || myCpu.getMaxClockSpeed() == null) {
            CPU tmpCpu = this.machineInformationService.getCpuLoadARMv7();

            myCpu.setMaxClockSpeed(tmpCpu.getMaxClockSpeed());
            myCpu.setCurrentClockSpeed(tmpCpu.getCurrentClockSpeed());
            myCpu.setLoadPercentage(tmpCpu.calcLoad());
        }

        MonitoringMessage monitoringMessage = new MonitoringMessage(myCpu, myMem);
        webSocket.convertAndSend("/topic/monitoring", monitoringMessage.toHashMap());
    }


}
