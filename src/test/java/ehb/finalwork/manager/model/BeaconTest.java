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
        assertNull(beacon.getLocation());
        assertEquals("beacon", beacon.getTableName());
    }

    @Test
    public void completedConstructorTest() {
        Vector3 vec3 = new Vector3(2.0, 2.0, 2.0);
        beacon = new Beacon("id", "room_id", "name", "desc", 1L, 1L, 1.0, vec3);

        assertEquals("id", beacon.getId());
        assertEquals("room_id", beacon.getRoomId());
        assertEquals("name", beacon.getName());
        assertEquals("desc", beacon.getDescription());
        assertEquals(1, beacon.getMajor(), 0);
        assertEquals(1, beacon.getMinor(), 0);
        assertEquals(1.0, beacon.getCalibrationFactor(), 0);
        assertSame(vec3, beacon.getLocation());
        assertEquals("beacon", beacon.getTableName());
    }

    @Test
    public void jsonInitTest() throws IOException {
        Vector3 vec3 = new Vector3(1.0, 2.0, 3.0);
        HashMap<String, Object> json = new HashMap<String, Object>();
        json.put("id", "id");
        json.put("roomId", "room_id");
        json.put("name", "name");
        json.put("description", "desc");
        json.put("calibration_factor", 1.0);
        json.put("location", vec3);

        beacon = new ObjectMapper().readValue(TestUtil.convertObjectToJsonString(json), Beacon.class);

        assertEquals("id", beacon.getId());
        assertEquals("room_id", beacon.getRoomId());
        assertEquals("name", beacon.getName());
        assertEquals("desc", beacon.getDescription());
        assertEquals(1.0, beacon.getCalibrationFactor(), 0);
        assertEquals(1.0, beacon.getLocation().x, 0);
        assertEquals(2.0, beacon.getLocation().y, 0);
        assertEquals(3.0, beacon.getLocation().z, 0);
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
        beacon.setCalibrationFactor(2.2);
        assertEquals(2.2, beacon.getCalibrationFactor(), 0);
    }

    @Test
    public void locationFactorSetterAndGetterTest() {
        Vector3 vec3 = new Vector3(3.2, 3.3, 3.4);

        beacon.setLocation(vec3);
        assertSame( vec3, beacon.getLocation());
    }

    @Test
    public void tableNameIsSetTest() {
        assertEquals("beacon", beacon.getTableName());
    }
}
