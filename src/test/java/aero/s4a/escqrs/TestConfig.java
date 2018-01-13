package aero.s4a.escqrs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    TestEventListener testEventListener() {
        return new TestEventListener();
    }
}
