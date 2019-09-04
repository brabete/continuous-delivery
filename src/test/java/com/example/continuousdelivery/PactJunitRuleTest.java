package com.example.continuousdelivery;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.continuousdelivery.domain.User;
import com.example.continuousdelivery.service.UserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PactJunitRuleTest {

    @Autowired
    UserService userService;

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("ExampleProvider", this);

    @Pact(consumer = "JunitRuleConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
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
                        "           \"ROLE_ADMIN\",\n" +
                        "      ]\n" +
                        "}")
                .toPact();
    }

    @Test
    @PactVerification
    public void runTest() {
//        userService.setBackendURL(mockProvider.getUrl());
//        User user = userService.getUser();
//        assertEquals(user.getLogin(), "admin");
    }
}
