package net.marco27.api.postgrescoingecko.repository;

import net.marco27.api.postgrescoingecko.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricesRepository extends JpaRepository<Prices, Long> {
}
