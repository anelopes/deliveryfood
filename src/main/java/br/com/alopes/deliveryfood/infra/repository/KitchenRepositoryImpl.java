package br.com.alopes.deliveryfood.infra.repository;

import br.com.alopes.deliveryfood.domain.model.Kitchen;
import br.com.alopes.deliveryfood.domain.repository.KitchenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext //Injeta o EntityManager com algumas configurações inciais
    private EntityManager entityManager;

    @Override
    public List<Kitchen> findAll() {
        return entityManager.createQuery("from Kitchen", Kitchen.class).getResultList(); //JPQL -> consulta pelo objeto
    }

    @Override
    public Kitchen findById(Long id) {
        return entityManager.find(Kitchen.class, id);
    }

    @Transactional
    @Override
    public Kitchen save(Kitchen kitchen) {
        return entityManager.merge(kitchen);
    }

    @Transactional
    @Override
    public void delete(Kitchen kitchen) {
        kitchen = findById(kitchen.getId());
        entityManager.remove(kitchen);
    }
}
