package ehb.finalwork.manager.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class RethinkReturnObjectTest {

    private RethinkReturnObject returnObject;
    private List<RethinkChangesReturn> changes;

    @Before
    public void setUp() {
        returnObject = new RethinkReturnObject();
        changes = new ArrayList<RethinkChangesReturn>();
        changes.add(new RethinkChangesReturn());
    }

    @Test
    public void emptyConstructorAndGettersTest() {
        returnObject = new RethinkReturnObject();

        assertNull(returnObject.getInserted());
        assertNull(returnObject.getReplaced());
        assertNull(returnObject.getUnchanged());
        assertNull(returnObject.getSkipped());
        assertNull(returnObject.getErrors());
        assertNull(returnObject.getDeleted());
        assertTrue(returnObject.getChanges().isEmpty());
    }

    @Test
    public void completedConstructorTest() {
        returnObject = new RethinkReturnObject(1L, 2L, 3L, 4L, 5L, 6L, changes);

        assertEquals((Long) 1L, returnObject.getInserted());
        assertEquals((Long) 2L, returnObject.getReplaced());
        assertEquals((Long) 3L, returnObject.getUnchanged());
        assertEquals((Long) 4L, returnObject.getSkipped());
        assertEquals((Long) 5L, returnObject.getErrors());
        assertEquals((Long) 6L, returnObject.getDeleted());
        assertSame(changes, returnObject.getChanges());
    }

    @Test
    public void insertedGettersAndSetterTest() {
        returnObject.setInserted(9L);
        assertEquals((Long) 9L, returnObject.getInserted());
    }

    @Test
    public void replacedGettersAndSetterTest() {
        returnObject.setReplaced(9L);
        assertEquals((Long) 9L, returnObject.getReplaced());
    }

    @Test
    public void unchangedGettersAndSetterTest() {
        returnObject.setUnchanged(9L);
        assertEquals((Long) 9L, returnObject.getUnchanged());
    }

    @Test
    public void skippedGettersAndSetterTest() {
        returnObject.setSkipped(9L);
        assertEquals((Long) 9L, returnObject.getSkipped());
    }

    @Test
    public void errorsGettersAndSetterTest() {
        returnObject.setErrors(9L);
        assertEquals((Long) 9L, returnObject.getErrors());
    }

    @Test
    public void deletedGettersAndSetterTest() {
        returnObject.setDeleted(9L);
        assertEquals((Long) 9L, returnObject.getDeleted());
    }

    @Test
    public void changesGettersAndSetterTest() {

        List<HashMap> changesList = new ArrayList<HashMap>();
        HashMap<String, HashMap> hashChange = new HashMap<String, HashMap>();
        HashMap<String, String> hashChangeVal = new HashMap<String, String>();

        hashChangeVal.put("key_1", "val_1");
        hashChange.put("old_val", hashChangeVal);
        hashChange.put("new_val", hashChangeVal);
        changesList.add(hashChange);

        returnObject.setChanges(changesList);
        assertEquals("val_1", returnObject.getChanges().get(0).getNewVal().get("key_1"));
        assertEquals("val_1", returnObject.getFirstChange().getNewVal().get("key_1"));
    }
}
