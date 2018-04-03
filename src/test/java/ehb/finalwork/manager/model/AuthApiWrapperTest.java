package ehb.finalwork.manager.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;

public class AuthApiWrapperTest {

    @Test
    public void constructorAndGettersTest() {
        AuthAPIWrapper apiWrapper = new AuthAPIWrapper("https://google.com/", "", "", "connection", "audience");

        assertEquals("connection", apiWrapper.getConnection());
        assertEquals("audience", apiWrapper.getAudience());
    }
}
