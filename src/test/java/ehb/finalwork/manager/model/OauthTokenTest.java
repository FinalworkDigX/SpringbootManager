package ehb.finalwork.manager.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import ehb.finalwork.manager.TestUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class OauthTokenTest {

    private OauthToken token;

    @Before
    public void SetUp() {
        token = new OauthToken();
    }

    @Test
    public void emptyConstructorAndGettersTest() {
        token = new OauthToken();

        assertNull(token.getAccessToken());
        assertNull(token.getScope());
        assertNull(token.getExpiresIn());
        assertNull(token.getTokenType());
    }

    @Test
    public void jsonInitTest() throws IOException {
        HashMap<String, String> json = new HashMap<String, String>();
        json.put("access_token", "token");
        json.put("scope", "scope:1 scope:2");
        json.put("expires_in", "expires_in");
        json.put("token_type", "token_type");

        token = new ObjectMapper().readValue(TestUtil.convertObjectToJsonString(json), OauthToken.class);

        assertEquals("token", token.getAccessToken());
        assertEquals(2, token.getScope().size());
        assertEquals("scope:1", token.getScope().get(0));
        assertEquals("scope:2", token.getScope().get(1));
        assertEquals("expires_in", token.getExpiresIn());
        assertEquals("token_type", token.getTokenType());
    }

    @Test
    public void informationSetterAndGetterTest() {

        token.setScope("scope_test");
        assertEquals(1, token.getScope().size());
        assertEquals("scope_test", token.getScope().get(0));
    }
}