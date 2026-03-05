package in.gov.vocport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PosBridgeConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}



