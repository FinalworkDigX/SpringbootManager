package ehb.finalwork.manager.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import ehb.finalwork.manager.TestUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class RethinkChangesReturnTest {

    private RethinkChangesReturn changesReturn;
    private HashMap<String, String> hashMap;

    @Before
    public void setUp() {
        changesReturn = new RethinkChangesReturn();
        hashMap = new HashMap<String, String>();
        hashMap.put("key_1", "val_1");
    }

    @Test
    public void emptyConstructorAndGettersTest() {
        changesReturn = new RethinkChangesReturn();

        assertNull(changesReturn.getNewVal());
        assertNull(changesReturn.getOldVal());
    }

    @Test
    public void completedConstructorTest() {

        changesReturn = new RethinkChangesReturn(hashMap, hashMap);

        assertSame(hashMap, changesReturn.getNewVal());
        assertSame(hashMap, changesReturn.getOldVal());
    }

    @Test
    public void jsonInitTest() throws IOException {
        HashMap<String, HashMap> json = new HashMap<String, HashMap>();

        HashMap<String, String> oldVal = new HashMap<String, String>(hashMap);
        HashMap<String, String> newVal = new HashMap<String, String>(hashMap);
        newVal.put("key_1", "val_2");

        json.put("old_val", oldVal);
        json.put("new_val", newVal);

        changesReturn = new ObjectMapper().readValue(TestUtil.convertObjectToJsonString(json), RethinkChangesReturn.class);

        assertEquals("val_1", changesReturn.getOldVal().get("key_1"));
        assertEquals("val_2", changesReturn.getNewVal().get("key_1"));
    }

    @Test
    public void newValGetterAndSetterTest() {
        changesReturn.setNewVal(hashMap);
        assertSame(hashMap, changesReturn.getNewVal());
    }

    @Test
    public void oldValGetterAndSetterTest() {
        changesReturn.setOldVal(hashMap);
        assertSame(hashMap, changesReturn.getOldVal());
    }
}
