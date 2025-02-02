package net.marco27.api.postgrescoingecko.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "prices")
@Data
@NoArgsConstructor
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String instrument;
    private Long amount;
    private Date creates;
}
