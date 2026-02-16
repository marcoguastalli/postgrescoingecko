package net.marco27.api.postgrescoingecko.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static net.marco27.api.postgrescoingecko.utils.LoggerUtils.logInfoTrackingId;

@Service
@Slf4j
public class ApiService {
    private final RestTemplate restTemplate;

    public ApiService(@NonNull RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public byte[] getJson(@NonNull String trackingId, @NonNull String url) throws RestClientResponseException {
        try {
            logInfoTrackingId(log, trackingId, format("Call url: %s", url));
            return restTemplate.getForObject(url, byte[].class);
        } catch (RestClientResponseException e) {
            final String message = String.format("Error get response from url: %s", url);
            log.error(message);
            throw e;
        }
    }

}
