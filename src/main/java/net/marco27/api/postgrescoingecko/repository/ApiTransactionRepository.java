package net.marco27.api.postgrescoingecko.repository;

import net.marco27.api.postgrescoingecko.model.ApiTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiTransactionRepository extends JpaRepository<ApiTransaction, Long> {
}