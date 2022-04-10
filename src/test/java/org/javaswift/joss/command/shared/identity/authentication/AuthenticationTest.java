package org.javaswift.joss.command.shared.identity.authentication;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AuthenticationTest {

    @Test
    public void testMarshalling() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        Authentication auth = new Authentication("testtenant", "testtenantid", "testuser", "testpassword");
        assertEquals("{\"auth\":{\"passwordCredentials\":{\"username\":\"testuser\",\"password\":\"testpassword\"},\"tenantName\":\"testtenant\",\"tenantId\":\"testtenantid\"}}", mapper.writeValueAsString(auth));
    }

}
