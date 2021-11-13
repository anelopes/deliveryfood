package br.com.alopes.deliveryfood.infra.repository;

import br.com.alopes.deliveryfood.domain.model.Restaurant;
import br.com.alopes.deliveryfood.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext //Injeta o EntityManager com algumas configurações inciais
    private EntityManager entityManager;

    @Override
    public List<Restaurant> findAll() {
        return entityManager.createQuery("from Restaurant", Restaurant.class).getResultList(); //JPQL -> consulta pelo objeto
    }

    @Override
    public Restaurant findById(Long id) {
        return entityManager.find(Restaurant.class, id);
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        return entityManager.merge(restaurant);
    }

    @Transactional
    @Override
    public void delete(Restaurant restaurant) {
        restaurant = findById(restaurant.getId());
        entityManager.remove(restaurant);
    }
}
