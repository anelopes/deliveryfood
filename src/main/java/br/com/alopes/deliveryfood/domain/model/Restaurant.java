package br.com.alopes.deliveryfood.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal freightRate; // taxa de frete

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFreightRate() {
        return freightRate;
    }

    public void setFreightRate(BigDecimal freightRate) {
        this.freightRate = freightRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant kitchen = (Restaurant) o;
        return id.equals(kitchen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
