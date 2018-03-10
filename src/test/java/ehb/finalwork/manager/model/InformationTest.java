package ehb.finalwork.manager.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InformationTest {

    private Information info;

    @Before
    public void SetUp() {
        info = new Information();
    }

    @Test
    public void emptyConstructorTest() {
        info = new Information();

        assertNull(info.getName());
        assertNull(info.getData());
        assertNull(info.getIndex());
    }

    @Test
    public void completedConstructorTest() {
        info = new Information("name", "data", 1L);

        assertEquals("name", info.getName());
        assertEquals("data", info.getData());
        assertEquals((Long) 1L, info.getIndex());
    }

    @Test
    public void nameSetterAndGetterTest() {
        info.setName("name_1");
        assertEquals("name_1", info.getName());
    }

    @Test
    public void dataSetterAndGetterTest() {
        info.setData("data_1");
        assertEquals("data_1", info.getData());
    }

    @Test
    public void indexSetterAndGetterTest() {
        info.setIndex(2L);
        assertEquals((Long) 2L, info.getIndex());
    }
}
