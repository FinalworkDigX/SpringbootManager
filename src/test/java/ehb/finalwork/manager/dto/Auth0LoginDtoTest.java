package ehb.finalwork.manager.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Auth0LoginDtoTest {
    private Auth0LoginDto loginDto;

    @Before
    public void setUp() {
        loginDto = new Auth0LoginDto();
    }

    @Test
    public void emptyConstructorAndGettersTest() {
        loginDto = new Auth0LoginDto();

        assertNull(loginDto.getEmail());
        assertNull(loginDto.getPassword());
    }

    @Test
    public void completedConstructorTest() {
        loginDto = new Auth0LoginDto("email", "password");

        assertEquals("email", loginDto.getEmail());
        assertEquals("password", loginDto.getPassword());
    }

    @Test
    public void emailGetterAndSetter() {
        loginDto.setEmail("email_1");
        assertEquals("email_1", loginDto.getEmail());
    }

    @Test
    public void passwordGetterAndSetter() {
        loginDto.setPassword("password_1");
        assertEquals("password_1", loginDto.getPassword());
    }
}
