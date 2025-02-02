package net.marco27.api.postgrescoingecko.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.marco27.api.postgrescoingecko.exception.DocumentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ApiService {
    private final RestTemplate restTemplate;

    public ApiService(@NonNull RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public byte[] getJson(@NonNull String url) throws DocumentNotFoundException {
        try {
            log.info("Call url: {}", url);
            return restTemplate.getForObject(url, byte[].class);
        } catch (RestClientResponseException e) {
            final String message = String.format("Error get response from url: %s", url);
            log.error(message);
            throw new DocumentNotFoundException(message, e);
        }
    }

}
