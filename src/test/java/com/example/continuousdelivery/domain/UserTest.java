package com.example.continuousdelivery.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Test
    public void checkLoginValidator() {
        User user = new User();
        user.setLogin("123456789012345678901234567890123456789012345678901234567890");
        user.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        String violationMessage = validator.validateProperty(user, "login")
                .iterator()
                .next()
                .getMessage();

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("size must be between 1 and 50", violationMessage);
    }


    @Test
    public void createUserTest() {
        User user = new User();
        user.setLogin("user");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@mail.com");
        user.setActivated(true);

        Assert.assertEquals("user", user.getLogin());
        Assert.assertEquals("John", user.getFirstName());
        Assert.assertEquals("Doe", user.getLastName());
        Assert.assertEquals("john.doe@mail.com", user.getEmail());
        Assert.assertTrue(user.getActivated());
    }
}
