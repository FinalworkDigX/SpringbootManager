package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.geom.Vec3d;
import ehb.finalwork.manager.TestUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class RethinkBeaconDtoTest {

    private RethinkBeaconDto beaconDto;

    @Before
    public void SetUp() {
        beaconDto = new RethinkBeaconDto();
    }

    @Test
    public void emptyConstructorTest() {
        beaconDto = new RethinkBeaconDto();

        assertNull(beaconDto.getRoomId());
        assertNull(beaconDto.getName());
        assertNull(beaconDto.getDescription());
        assertNull(beaconDto.getCalibrationFactor());
        assertNull(beaconDto.getLocation());
    }

    @Test
    public void completedConstructorTest() {
        Vec3d vec3 = new Vec3d(2.0, 2.0, 2.0);
        beaconDto = new RethinkBeaconDto("room_id", "name" , "desc", 1.0, vec3);

        assertEquals("room_id", beaconDto.getRoomId());
        assertEquals("name", beaconDto.getName());
        assertEquals("desc", beaconDto.getDescription());
        assertEquals(1.0, beaconDto.getCalibrationFactor(),0);
        assertSame(vec3, beaconDto.getLocation());
    }

    @Test
    public void jsonInitTest() throws IOException {
        Vec3d vec3 = new Vec3d(1.0, 2.0, 3.0);
        HashMap<String, Object> json = new HashMap<String, Object>();
        json.put("room_id", "room_id");
        json.put("name", "name");
        json.put("description", "desc");
        json.put("calibration_factor", 1.0);
        json.put("location", vec3);

        beaconDto = new ObjectMapper().readValue(TestUtil.convertObjectToJsonString(json), RethinkBeaconDto.class);

        assertEquals("room_id", beaconDto.getRoomId());
        assertEquals("name", beaconDto.getName());
        assertEquals("desc", beaconDto.getDescription());
        assertEquals(1.0, beaconDto.getCalibrationFactor(), 0);
        assertEquals(1.0, beaconDto.getLocation().x, 0);
        assertEquals(2.0, beaconDto.getLocation().y, 0);
        assertEquals(3.0, beaconDto.getLocation().z, 0);
    }

    @Test
    public void roomIdSetterAndGetterTest() {
        beaconDto.setRoomId("name_1");
        assertEquals("name_1", beaconDto.getRoomId());
    }

    @Test
    public void nameSetterAndGetterTest() {
        beaconDto.setName("name_1");
        assertEquals("name_1", beaconDto.getName());
    }

    @Test
    public void descriptionSetterAndGetterTest() {
        beaconDto.setDescription("desc_1");
        assertSame("desc_1", beaconDto.getDescription());
    }

    @Test
    public void calibrationFactorSetterAndGetterTest() {
        beaconDto.setCalibrationFactor(2.2);
        assertEquals(2.2, beaconDto.getCalibrationFactor(),0);
    }

    @Test
    public void locationFactorSetterAndGetterTest() {
        Vec3d vec3 = new Vec3d(3.2, 3.3, 3.4);

        beaconDto.setLocation(vec3);
        assertSame( vec3, beaconDto.getLocation());
    }
}
