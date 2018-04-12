package ehb.finalwork.manager.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import ehb.finalwork.manager.TestUtil;
import ehb.finalwork.manager.model.Vector3;
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
    }

    @Test
    public void completedConstructorTest() {
        beaconDto = new RethinkBeaconDto("room_id", "name" , "desc", 1L, 1L, 1L);

        assertEquals("room_id", beaconDto.getRoomId());
        assertEquals("name", beaconDto.getName());
        assertEquals("desc", beaconDto.getDescription());
        assertEquals(1, beaconDto.getMajor(),0);
        assertEquals(1, beaconDto.getMinor(),0);
        assertEquals(1, beaconDto.getCalibrationFactor(),0);
    }

    @Test
    public void jsonInitTest() throws IOException {
        HashMap<String, Object> json = new HashMap<String, Object>();
        json.put("roomId", "room_id");
        json.put("name", "name");
        json.put("description", "desc");
        json.put("calibrationFactor", 1.0);

        beaconDto = new ObjectMapper().readValue(TestUtil.convertObjectToJsonString(json), RethinkBeaconDto.class);

        assertEquals("room_id", beaconDto.getRoomId());
        assertEquals("name", beaconDto.getName());
        assertEquals("desc", beaconDto.getDescription());
        assertEquals(1.0, beaconDto.getCalibrationFactor(), 0);
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
        beaconDto.setCalibrationFactor(2L);
        assertEquals(2, beaconDto.getCalibrationFactor(),0);
    }
}
