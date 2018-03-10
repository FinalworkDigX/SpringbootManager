package ehb.finalwork.manager.model;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DataLogTest {

    private DataLog dataLog;

    @Before
    public void SetUp() {
        dataLog = new DataLog();
    }

    @Test
    public void emptyConstructorTest() {
        dataLog = new DataLog();

        assertNull(dataLog.getId());
        assertNull(dataLog.getItemId());
        assertNull(dataLog.getInformation());
        assertNull(dataLog.getTimestamp());
        assertEquals("dataLog", dataLog.getTableName());
    }

    @Test
    public void completedConstructorTest() {
        List<Information> infoList = new ArrayList<Information>();
        dataLog = new DataLog("id", "item_id", infoList , 1L);

        assertEquals("id", dataLog.getId());
        assertEquals("item_id", dataLog.getItemId());
        assertSame(infoList, dataLog.getInformation());
        assertEquals((Long) 1L, dataLog.getTimestamp());
        assertEquals("dataLog", dataLog.getTableName());
    }

    @Test
    public void idSetterAndGetterTest() {
        dataLog.setId("id_1");
        assertEquals("id_1", dataLog.getId());
    }

    @Test
    public void itemIdSetterAndGetterTest() {
        dataLog.setItemId("item_id_1");
        assertEquals("item_id_1", dataLog.getItemId());
    }

    @Test
    public void informationSetterAndGetterTest() {

        List<Information> infoList = Collections.singletonList(new Information("info_name", "info_data", 1L));

        dataLog.setInformation(infoList);
        assertSame(infoList, dataLog.getInformation());
    }

    @Test
    public void timeStampSetterAndGetterTest() {
        dataLog.setTimestamp(2L);
        assertEquals((Long) 2L, dataLog.getTimestamp());
    }

    @Test
    public void tableNameIsSetTest() {
        assertEquals("dataLog", dataLog.getTableName());
    }
}
