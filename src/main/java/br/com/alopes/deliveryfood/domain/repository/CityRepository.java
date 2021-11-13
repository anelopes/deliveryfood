package br.com.alopes.deliveryfood.domain.repository;

import br.com.alopes.deliveryfood.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> findAll();

    City findById(Long id);

    City save(City city);

    void delete(Long id);

}
