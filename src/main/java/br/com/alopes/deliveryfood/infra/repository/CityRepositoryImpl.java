package br.com.alopes.deliveryfood.infra.repository;

import br.com.alopes.deliveryfood.domain.model.City;
import br.com.alopes.deliveryfood.domain.repository.CityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext //Injeta o EntityManager com algumas configurações inciais
    private EntityManager entityManager;

    @Override
    public List<City> findAll() {
        return entityManager.createQuery("from City", City.class).getResultList(); //JPQL -> consulta pelo objeto
    }

    @Override
    public City findById(Long id) {
        return entityManager.find(City.class, id);
    }

    @Transactional
    @Override
    public City save(City city) {
        return entityManager.merge(city);
    }

    @Transactional
    @Override
    public void delete(City city) {
        city = findById(city.getId());
        entityManager.remove(city);
    }
}
