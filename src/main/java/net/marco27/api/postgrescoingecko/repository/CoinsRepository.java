package net.marco27.api.postgrescoingecko.repository;

import net.marco27.api.postgrescoingecko.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinsRepository extends JpaRepository<Coin, Long> {
}
