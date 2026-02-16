package net.marco27.api.postgrescoingecko.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Data
@Configuration
@ConfigurationProperties("web")
public class ApplicationYmlConfig {

    private String version;
    private String urlToCall;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
