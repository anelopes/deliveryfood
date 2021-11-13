package br.com.alopes.deliveryfood.infra.repository;

import br.com.alopes.deliveryfood.domain.model.State;
import br.com.alopes.deliveryfood.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext //Injeta o EntityManager com algumas configurações inciais
    private EntityManager entityManager;

    @Override
    public List<State> findAll() {
        return entityManager.createQuery("from State", State.class).getResultList(); //JPQL -> consulta pelo objeto
    }

    @Override
    public State findById(Long id) {
        return entityManager.find(State.class, id);
    }

    @Transactional
    @Override
    public State save(State state) {
        return entityManager.merge(state);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        State state = findById(id);

        if (state == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(state);
    }
}
