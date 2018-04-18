package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import ehb.finalwork.manager.TestUtil;
import ehb.finalwork.manager.model.DataLog;
import ehb.finalwork.manager.model.Information;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class RethinkDataLogDtoTest {

    private RethinkDataLogDto dataLogDto;

    @Before
    public void SetUp() {
        dataLogDto = new RethinkDataLogDto();
    }

    @Test
    public void emptyConstructorTest() {
        dataLogDto = new RethinkDataLogDto();

        assertNull(dataLogDto.getItemId());
        assertTrue(dataLogDto.getInformation().isEmpty());
        assertNull(dataLogDto.getTimestamp());
    }

    @Test
    public void completedConstructorTest() {
        List<Information> infoList = new ArrayList<Information>();
        dataLogDto = new RethinkDataLogDto("item_id", infoList , 1L);

        assertEquals("item_id", dataLogDto.getItemId());
        assertSame(infoList, dataLogDto.getInformation());
        assertEquals((Long) 1L, dataLogDto.getTimestamp());
    }

    @Test
    public void jsonInitTest() throws IOException {
        List<Information> infoList = Collections.singletonList(new Information("info_name", "info_data", 1L));
        HashMap<String, Object> json = new HashMap<String, Object>();
        json.put("itemId", "item_id");
        json.put("information", infoList);
        json.put("timestamp", 123L);

        dataLogDto = new ObjectMapper().readValue(TestUtil.convertObjectToJsonString(json), RethinkDataLogDto.class);

        assertEquals("item_id", dataLogDto.getItemId());
        assertEquals(1, dataLogDto.getInformation().size());
        assertEquals("info_name", dataLogDto.getInformation().get(0).getName());
        assertEquals("info_data", dataLogDto.getInformation().get(0).getData());
        assertEquals((Long) 1L, dataLogDto.getInformation().get(0).getIndex());
        assertEquals((Long) 123L, dataLogDto.getTimestamp());
    }

    @Test
    public void itemIdSetterAndGetterTest() {
        dataLogDto.setItemId("item_id_1");
        assertEquals("item_id_1", dataLogDto.getItemId());
    }

    @Test
    public void informationSetterAndGetterTest() {

        List<Information> infoList = Collections.singletonList(new Information("info_name", "info_data", 1L));

        dataLogDto.setInformation(infoList);
        assertSame(infoList, dataLogDto.getInformation());
    }

    @Test
    public void timeStampSetterAndGetterTest() {
        dataLogDto.setTimestamp(2L);
        assertEquals((Long) 2L, dataLogDto.getTimestamp());
    }
}
