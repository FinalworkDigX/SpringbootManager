package ehb.finalwork.manager.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    private Room room;

    @Before
    public void SetUp() {
        room = new Room();
    }

    @Test
    public void emptyConstructorTest() {
        room = new Room();

        assertNull(room.getId());
        assertNull(room.getName());
        assertNull(room.getDescription());
        assertNull(room.getDescription());
        assertEquals("room", room.getTableName());
    }

    @Test
    public void completedConstructorTest() {
        room = new Room("id", "name", "desc" , "loc");

        assertEquals("id", room.getId());
        assertEquals("name", room.getName());
        assertEquals("desc", room.getDescription());
        assertEquals("loc", room.getLocation());
        assertEquals("room", room.getTableName());
    }

    @Test
    public void idSetterAndGetterTest() {
        room.setId("id_1");
        assertEquals("id_1", room.getId());
    }

    @Test
    public void nameSetterAndGetterTest() {
        room.setName("name_1");
        assertEquals("name_1", room.getName());
    }

    @Test
    public void descriptionSetterAndGetterTest() {
        room.setDescription("desc_1");
        assertEquals("desc_1", room.getDescription());
    }

    @Test
    public void locationSetterAndGetterTest() {
        room.setLocation("loc_1");
        assertEquals("loc_1", room.getLocation());
    }

    @Test
    public void tableNameIsSetTest() {
        assertEquals("room", room.getTableName());
    }
}
