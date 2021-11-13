package br.com.alopes.deliveryfood.domain.repository;

import br.com.alopes.deliveryfood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant save(Restaurant restaurant);

    void delete(Long id);

}
