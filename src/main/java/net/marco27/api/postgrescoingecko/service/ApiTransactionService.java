package net.marco27.api.postgrescoingecko.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.marco27.api.postgrescoingecko.model.ApiTransaction;
import net.marco27.api.postgrescoingecko.repository.CoinsRepository;
import net.marco27.api.postgrescoingecko.repository.ApiTransactionRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApiTransactionService {
    private final ApiTransactionRepository apiTransactionRepository;

    public ApiTransactionService(@NonNull ApiTransactionRepository apiTransactionRepository) {
        this.apiTransactionRepository = apiTransactionRepository;
    }

    public ApiTransaction save(@NonNull ApiTransaction apiTransaction) {
        return apiTransactionRepository.save(apiTransaction);
    }
}
