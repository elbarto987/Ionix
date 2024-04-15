package Ionix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:messages.properties")
public class AppConfig {
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
