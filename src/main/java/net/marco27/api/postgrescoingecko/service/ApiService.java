package net.marco27.api.postgrescoingecko.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.marco27.api.postgrescoingecko.exception.DocumentNotFoundException;
import net.marco27.api.postgrescoingecko.model.Coin;
import net.marco27.api.postgrescoingecko.repository.CoinsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ApiService {
    private final RestTemplate restTemplate;
    private final CoinsRepository coinsRepository;

    public ApiService(@NonNull RestTemplate restTemplate,
                      @NonNull CoinsRepository coinsRepository) {
        this.restTemplate = restTemplate;
        this.coinsRepository = coinsRepository;
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

    public List<Coin> saveAll(@NonNull List<Coin> coins) {
        return coinsRepository.saveAll(coins);
    }
}
