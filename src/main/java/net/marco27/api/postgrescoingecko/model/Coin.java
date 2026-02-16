package net.marco27.api.postgrescoingecko.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coins")
@Data
@NoArgsConstructor
public class Coin {

    @Id
    private String id;
    private String symbol;
    private String name;
}
