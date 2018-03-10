package ehb.finalwork.manager.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;

public class MgmtApiWrapperTest {

    @Test
    public void constructorAndGettersTest() {
        MgmtAPIWrapper mgmtAPIWrapper = new MgmtAPIWrapper("https://google.com/", "", "connection");

        assertEquals("connection", mgmtAPIWrapper.getConnection());
    }
}
