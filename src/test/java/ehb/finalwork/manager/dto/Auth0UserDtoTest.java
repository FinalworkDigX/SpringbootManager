package ehb.finalwork.manager.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Auth0UserDtoTest {
    private Auth0UserDto userDto;

    @Before
    public void setUp() {
        userDto = new Auth0UserDto();
    }

    @Test
    public void emptyConstructorAndGettersTest() {
        userDto = new Auth0UserDto();

        assertNull(userDto.getErrorMessage());
    }

    @Test
    public void completedConstructorTest() {
        userDto = new Auth0UserDto("message");

        assertEquals("message", userDto.getErrorMessage());
    }

    @Test
    public void emailGetterAndSetter() {
        userDto.setErrorMessage("message_1");
        assertEquals("message_1", userDto.getErrorMessage());
    }
}
