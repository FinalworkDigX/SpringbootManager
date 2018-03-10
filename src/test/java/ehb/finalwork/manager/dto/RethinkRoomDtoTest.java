package ehb.finalwork.manager.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RethinkRoomDtoTest {

    private RethinkRoomDto roomDto;

    @Before
    public void SetUp() {
        roomDto = new RethinkRoomDto();
    }

    @Test
    public void emptyConstructorTest() {
        roomDto = new RethinkRoomDto();

        assertNull(roomDto.getName());
        assertNull(roomDto.getDescription());
        assertNull(roomDto.getDescription());
    }

    @Test
    public void completedConstructorTest() {
        roomDto = new RethinkRoomDto("name", "desc" , "loc");

        assertEquals("name", roomDto.getName());
        assertEquals("desc", roomDto.getDescription());
        assertEquals("loc", roomDto.getLocation());
    }

    @Test
    public void nameSetterAndGetterTest() {
        roomDto.setName("name_1");
        assertEquals("name_1", roomDto.getName());
    }

    @Test
    public void descriptionSetterAndGetterTest() {
        roomDto.setDescription("desc_1");
        assertEquals("desc_1", roomDto.getDescription());
    }

    @Test
    public void locationSetterAndGetterTest() {
        roomDto.setLocation("loc_1");
        assertEquals("loc_1", roomDto.getLocation());
    }
}
