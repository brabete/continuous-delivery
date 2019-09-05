package com.example.continuousdelivery.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:postgresql:10-alpine://testcontainers/test",
        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
})
public class UserRepositoryIT {

    @Autowired
    UserRepository userRepository;



    @Test
    public void testEmptyIfNoKey() {
        assertThat(userRepository.findOneByLogin("null").isPresent()).isFalse();
    }
}
