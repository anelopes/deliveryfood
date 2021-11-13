package br.com.alopes.deliveryfood.domain.repository;

import br.com.alopes.deliveryfood.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> findAll();

    Kitchen findById(Long id);

    Kitchen save(Kitchen kitchen);

    void delete(Long id);

}
