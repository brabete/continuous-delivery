package com.example.continuousdelivery.cucumber;

import com.example.continuousdelivery.ContinuousDeliveryApplication;
import io.cucumber.java.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = ContinuousDeliveryApplication.class)
public class CucumberContextConfiguration {

    @Before
    public void spring_context(){
       // Need this empty method so that Cucumber can load the Spring Context properly
    }

}
