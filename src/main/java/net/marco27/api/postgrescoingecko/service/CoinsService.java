package net.marco27.api.postgrescoingecko.service;

import lombok.NonNull;
import net.marco27.api.postgrescoingecko.model.Coin;
import net.marco27.api.postgrescoingecko.repository.CoinsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinsService {
    private final CoinsRepository coinsRepository;

    public CoinsService(@NonNull CoinsRepository coinsRepository) {
        this.coinsRepository = coinsRepository;
    }

    public List<Coin> findAll() {
        return coinsRepository.findAll();
    }

    public List<Coin> saveAll(@NonNull List<Coin> coins) {
        return coinsRepository.saveAll(coins);
    }

}
