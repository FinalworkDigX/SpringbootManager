package ehb.finalwork.manager.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Auth0TokenHolderTest {
    private Auth0TokenHolder tokenHolder;

    @Before
    public void setUp() {
        tokenHolder = new Auth0TokenHolder();
    }

    @Test
    public void emptyConstructorAndGettersTest() {
        tokenHolder = new Auth0TokenHolder();

        assertNull(tokenHolder.getErrorMessage());
    }

    @Test
    public void completedConstructorTest() {
        tokenHolder = new Auth0TokenHolder("message");

        assertEquals("message", tokenHolder.getErrorMessage());
    }

    @Test
    public void emailGetterAndSetter() {
        tokenHolder.setErrorMessage("message_1");
        assertEquals("message_1", tokenHolder.getErrorMessage());
    }
}
