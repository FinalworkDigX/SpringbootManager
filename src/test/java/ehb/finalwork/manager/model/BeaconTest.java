package ehb.finalwork.manager.model;

import com.sun.javafx.geom.Vec3d;
import org.junit.Before;
import org.junit.Test;

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
        Vec3d vec3 = new Vec3d(2.0, 2.0, 2.0);
        beacon = new Beacon("id", "room_id", "name" , "desc", 1.0, vec3);

        assertEquals("id", beacon.getId());
        assertEquals("room_id", beacon.getRoomId());
        assertEquals("name", beacon.getName());
        assertEquals("desc", beacon.getDescription());
        assertEquals((Double) 1.0, beacon.getCalibrationFactor());
        assertSame(vec3, beacon.getLocation());
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
        assertEquals((Double) 2.2, beacon.getCalibrationFactor());
    }

    @Test
    public void locationFactorSetterAndGetterTest() {
        Vec3d vec3 = new Vec3d(3.2, 3.3, 3.4);

        beacon.setLocation(vec3);
        assertSame( vec3, beacon.getLocation());
    }

    @Test
    public void tableNameIsSetTest() {
        assertEquals("beacon", beacon.getTableName());
    }
}
