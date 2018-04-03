package ehb.finalwork.manager.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Auth0CreateUserDtoTest {

    private Auth0CreateUserDto createUserDto;

    @Before
    public void setUp() {
        createUserDto = new Auth0CreateUserDto();
    }

    @Test
    public void emptyConstructorAndGettersTest() {
        createUserDto = new Auth0CreateUserDto();

        assertNull(createUserDto.getErrorMessage());
    }

    @Test
    public void errorMessageGetterAndSetter() {
        createUserDto.setErrorMessage("message_1");
        assertEquals("message_1", createUserDto.getErrorMessage());
    }
}
