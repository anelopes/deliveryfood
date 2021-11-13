package br.com.alopes.deliveryfood.domain.repository;

import br.com.alopes.deliveryfood.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> findAll();

    State findById(Long id);

    State save(State kitchen);

    void delete(State kitchen);

}
