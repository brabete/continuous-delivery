package com.example.continuousdelivery;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.continuousdelivery.controller.UserResource;
import com.example.continuousdelivery.dto.UserDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PactJunitRuleTest {

    @Autowired
    UserResource userResource;

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("UserResource", this);

    @Pact(consumer = "JunitRuleConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder
                .given("")
                .uponReceiving("Example Pact interaction")
                .path("/api/users/admin")
                .method("GET")
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("{\n" +
                        "    \"id\": 3,\n" +
                        "    \"login\": \"admin\",\n" +
                        "    \"firstName\": \"Administrator\",\n" +
                        "    \"lastName\": \"Administrator\",\n" +
                        "    \"email\": \"admin@localhost\",\n" +
                        "    \"imageUrl\": \"\",\n" +
                        "    \"activated\": true,\n" +
                        "    \"langKey\": \"en\",\n" +
                        "    \"createdBy\": \"system\",\n" +
                        "    \"createdDate\": null,\n" +
                        "    \"lastModifiedBy\": \"system\",\n" +
                        "    \"lastModifiedDate\": null,\n" +
                        "    \"authorities\": [\n" +
                        "           \"ROLE_USER\",\n" +
                        "           \"ROLE_ADMIN\"\n" +
                        "      ]\n" +
                        "}")
                .toPact();
    }

    @Test
    @PactVerification
    public void runTest() throws IOException {
        // Given
        HttpUriRequest request = new HttpGet(mockProvider.getUrl() + "/api/users/admin");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        UserDTO user = retrieveResourceFromResponse(httpResponse, UserDTO.class);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));

        assertThat(user.getLogin(),
                equalTo("admin"));
    }

    public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz)
            throws IOException {

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, clazz);
    }
}
