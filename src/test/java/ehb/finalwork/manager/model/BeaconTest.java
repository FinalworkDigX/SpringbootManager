package ehb.finalwork.manager.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import ehb.finalwork.manager.TestUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class BeaconTest {

    private Beacon beacon;

    @Before
    public void SetUp() {
        beacon = new Beacon();
    }

    @Test
    public void emptyConstructorTest() {
        beacon = new Beacon();

        assertNull(beacon.getId());
        assertNull(beacon.getRoomId());
        assertNull(beacon.getName());
        assertNull(beacon.getDescription());
        assertNull(beacon.getCalibrationFactor());
        assertEquals("beacon", beacon.getTableName());
    }

    @Test
    public void completedConstructorTest() {
        beacon = new Beacon("id", "room_id", "name", "desc", 1L, 1L, 1L);

        assertEquals("id", beacon.getId());
        assertEquals("room_id", beacon.getRoomId());
        assertEquals("name", beacon.getName());
        assertEquals("desc", beacon.getDescription());
        assertEquals(1, beacon.getMajor(), 0);
        assertEquals(1, beacon.getMinor(), 0);
        assertEquals(1, beacon.getCalibrationFactor(), 0);
        assertEquals("beacon", beacon.getTableName());
    }

    @Test
    public void jsonInitTest() throws IOException {
        HashMap<String, Object> json = new HashMap<String, Object>();
        json.put("id", "id");
        json.put("roomId", "room_id");
        json.put("name", "name");
        json.put("description", "desc");
        json.put("calibrationFactor", 1.0);

        beacon = new ObjectMapper().readValue(TestUtil.convertObjectToJsonString(json), Beacon.class);

        assertEquals("id", beacon.getId());
        assertEquals("room_id", beacon.getRoomId());
        assertEquals("name", beacon.getName());
        assertEquals("desc", beacon.getDescription());
        assertEquals(1.0, beacon.getCalibrationFactor(), 0);
        assertEquals("beacon", beacon.getTableName());
    }

    @Test
    public void idSetterAndGetterTest() {
        beacon.setId("id_1");
        assertEquals("id_1", beacon.getId());
    }

    @Test
    public void roomIdSetterAndGetterTest() {
        beacon.setRoomId("name_1");
        assertEquals("name_1", beacon.getRoomId());
    }

    @Test
    public void nameSetterAndGetterTest() {
        beacon.setName("name_1");
        assertEquals("name_1", beacon.getName());
    }

    @Test
    public void descriptionSetterAndGetterTest() {
        beacon.setDescription("desc_1");
        assertSame("desc_1", beacon.getDescription());
    }

    @Test
    public void calibrationFactorSetterAndGetterTest() {
        beacon.setCalibrationFactor(2L);
        assertEquals(2, beacon.getCalibrationFactor(), 0);
    }

    @Test
    public void tableNameIsSetTest() {
        assertEquals("beacon", beacon.getTableName());
    }
}
