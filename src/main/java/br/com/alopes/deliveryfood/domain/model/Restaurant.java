package br.com.alopes.deliveryfood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //gera somente para os campos que especifica ser incluso
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal freightRate; // taxa de frete

    @ManyToOne
    private Kitchen kitchen;

}
