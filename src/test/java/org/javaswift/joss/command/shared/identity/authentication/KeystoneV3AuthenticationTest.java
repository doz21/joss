package org.javaswift.joss.command.shared.identity.authentication;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class KeystoneV3AuthenticationTest {

    @Test
    public void testMarshallingDefaultScope() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        mapper.setSerializationInclusion(Include.NON_NULL);

        KeystoneV3Authentication authentication = new KeystoneV3Authentication();
        authentication.setIdentity(new KeystoneV3Identity("username", "password", "domainName"));

        JsonNode expectedContent = mapper.readTree(
            getClass().getResourceAsStream("/sample-v3-auth-request.json")
        );

        JsonNode actualContent = mapper.valueToTree(authentication);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testMarshallingDomainScope() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        mapper.setSerializationInclusion(Include.NON_NULL);

        KeystoneV3Authentication authentication = new KeystoneV3Authentication();
        authentication.setIdentity(new KeystoneV3Identity("username", "password", "domainName"));
        authentication.setScope(new KeystoneV3DomainScope(new KeystoneV3Domain("domainName")));

        JsonNode expectedContent = mapper.readTree(
                getClass().getResourceAsStream("/sample-v3-auth-scope-domain-request.json")
        );

        JsonNode actualContent = mapper.valueToTree(authentication);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testMarshallingProjectScope() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        mapper.setSerializationInclusion(Include.NON_NULL);

        KeystoneV3Authentication authentication = new KeystoneV3Authentication();
        authentication.setIdentity(new KeystoneV3Identity("username", "password", "domainName"));
        authentication.setScope(new KeystoneV3ProjectScope("projectName", new KeystoneV3Domain("domainName")));

        JsonNode expectedContent = mapper.readTree(
                getClass().getResourceAsStream("/sample-v3-auth-scope-project-request.json")
        );

        JsonNode actualContent = mapper.valueToTree(authentication);

        assertEquals(expectedContent, actualContent);
    }

}
