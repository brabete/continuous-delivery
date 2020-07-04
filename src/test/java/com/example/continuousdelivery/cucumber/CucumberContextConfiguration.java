package com.example.continuousdelivery.cucumber;

import com.example.continuousdelivery.ContinuousDeliveryApplication;
import io.cucumber.java.Before;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ContinuousDeliveryApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:postgresql:11-alpine://testcontainers/test",
        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
})
public class CucumberContextConfiguration {

    @Before
    public void spring_context(){
       // Need this empty method so that Cucumber can load the Spring Context properly
    }

}
