package ehb.finalwork.manager.service;

import ehb.finalwork.manager.dto.InformationConversionDto;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
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

    public void convertData(HashMap<String, Object> payload, HashMap<String, Object> scheme) {
        log.info("PAYLOAD__: {}", payload);
        log.info("SCHEME__: {}", scheme);

        RethinkDataLogDto dataLogDto = new RethinkDataLogDto();

        scheme.forEach((key, value) -> {
            String dlKey = "";
            // Payload keys here

            ArrayList<String> payloadKeys = new ArrayList<>(Arrays.asList(key.split("\\.")));
            Object data = getPayloadData(payloadKeys, payload);

            // insert value in ifs
            if (value instanceof String) {
                if (value.equals("item_id")) {
                    dataLogDto.setItemId((String) data);
                }
                else {
                    log.error("Unrecognized value item: {}", value);
                }
            }
            else if (value instanceof InformationConversionDto) {
                Information dlInfo = new Information((InformationConversionDto) value);
                dlInfo.setData(data.toString());
                dataLogDto.addInformation(dlInfo);
            }
            else if (value instanceof HashMap) {
                Information dlInfo = new Information(((HashMap) value));
                dlInfo.setData(data.toString());
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
