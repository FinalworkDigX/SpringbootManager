package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dto.InformationConversionDto;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.ConversionSchemeEntry;
import ehb.finalwork.manager.model.DataLog;
import ehb.finalwork.manager.model.Information;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataConversionService {

    private Logger log = LoggerFactory.getLogger(getClass());

    // DATALOG SERVICE = NULL => same issue as StompClientService..
    // Can NOT use same 'hack' as DataLogServie requires DataLogDao, which requires other stuff..
    @Autowired
    DataLogService dataLogService;

    public void convertData(HashMap<String, Object> payload, List<ConversionSchemeEntry> scheme) {
        log.info("PAYLOAD__: {}", payload);
        log.info("SCHEME__: {}", scheme);

        RethinkDataLogDto dataLogDto = new RethinkDataLogDto();

        scheme.forEach((schemeEntry) -> {
            // Get Scheme entry info for shorter code
            String incomingDataKey = schemeEntry.getIncomingDataKey();
            Object dataLogData = schemeEntry.getDataLogData();


            // Get Conversion keys
            ArrayList<String> incomingDataKeys = new ArrayList<>(
                    Arrays.asList(
                            incomingDataKey.split("\\.")
                    )
            );
            Object payloadData = getPayloadData(incomingDataKeys, payload);

            // Convert data
            if (dataLogData instanceof String) {
                if (dataLogData.equals("item_id")) {
                    dataLogDto.setItemId((String) payloadData);
                }
                else {
                    log.error("Unrecognized value item: {}", dataLogData);
                }
            }
            // What it should be if RethinkDB works like it should..
            else if (dataLogData instanceof InformationConversionDto) {
                Information dlInfo = new Information((InformationConversionDto) dataLogData);
                dlInfo.setData(payloadData.toString());
                dataLogDto.addInformation(dlInfo);
            }
            else if (dataLogData instanceof HashMap) {
                Information dlInfo = new Information(((HashMap) dataLogData));
                dlInfo.setData(payloadData.toString());
                dataLogDto.addInformation(dlInfo);
            }
        });

        if (dataLogDto.getItemId() == null || dataLogDto.getItemId().isEmpty()) {
            log.error("No ItemId defined!");
            return;
        }

        try {
            dataLogService.create(dataLogDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getPayloadData(List<String> keys, HashMap<String, Object> payload) {
        log.info("keysize: {}", keys);
        if (keys.size() > 1) {
            String oldKey = keys.get(0);
            keys.remove(0);
            Object newPayload = payload.get(oldKey);
            return getPayloadData(keys, (HashMap<String, Object>) newPayload);
        }
        else {
            return payload.get(keys.get(0));
        }
    }
}
