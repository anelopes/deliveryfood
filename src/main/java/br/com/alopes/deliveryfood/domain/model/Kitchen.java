package br.com.alopes.deliveryfood.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonRootName("kitchen") //utilizado no xml
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //gera somente para os campos que especifica ser incluso
@Entity
public class Kitchen {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
