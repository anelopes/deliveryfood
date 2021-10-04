package br.com.alopes.deliveryfood.jpa;

import br.com.alopes.deliveryfood.domain.model.Kitchen;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RegisterKitchen {

    @PersistenceContext //Injeta o EntityManager com algumas configurações inciais
    private EntityManager entityManager;

    public List<Kitchen> findAll() {
        return entityManager.createQuery("from Kitchen", Kitchen.class).getResultList(); //JPQL -> consulta pelo objeto
    }

    public Kitchen findById(Long id) {
        return entityManager.find(Kitchen.class, id);
    }

    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return entityManager.merge(kitchen);
    }

    @Transactional
    public void delete(Kitchen kitchen) {
        kitchen = findById(kitchen.getId());
        entityManager.remove(kitchen);
    }
}
