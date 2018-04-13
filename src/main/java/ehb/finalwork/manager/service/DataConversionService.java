package ehb.finalwork.manager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DataConversionService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DataLogService dataLogService;

    public void convertData(HashMap<String, Object> payload, HashMap<String, Object> scheme) {
        log.info("PAYLOAD__: {}", payload);
        log.info("SCHEME__: {}", scheme);
    }
}
