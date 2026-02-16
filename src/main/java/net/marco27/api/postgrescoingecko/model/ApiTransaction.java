package net.marco27.api.postgrescoingecko.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "api-transactions")
@Data
@NoArgsConstructor
public class ApiTransaction {

    @Id
    private Timestamp created;
    private String transactionId;
    private Integer result;
    private Integer numberOfCoins;
    private String delta;

    /**
     * This method is called before the entity is saved to the DDBB creating the current timestamp
     */
    @PrePersist
    public void createId() {
        this.created = Timestamp.from(Instant.now());
    }
}
