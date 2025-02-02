package net.marco27.api.postgrescoingecko.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.marco27.api.postgrescoingecko.exception.DocumentNotFoundException;
import net.marco27.api.postgrescoingecko.model.Prices;
import net.marco27.api.postgrescoingecko.repository.PricesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ApiService {
    private final RestTemplate restTemplate;
    private final PricesRepository pricesRepository;

    public ApiService(@NonNull RestTemplate restTemplate, @NonNull PricesRepository pricesRepository) {
        this.restTemplate = restTemplate;
        this.pricesRepository = pricesRepository;
    }

    public byte[] getJson(@NonNull final String url) throws DocumentNotFoundException {
        try {
            log.info("Call url: {}", url);
            return restTemplate.getForObject(url, byte[].class);
        } catch (RestClientResponseException e) {
            final String message = String.format("Error get response from url: %s", url);
            log.error(message);
            throw new DocumentNotFoundException(message, e);
        }
    }

    public List<Prices> findAll() {
        return pricesRepository.findAll();
    }
}
