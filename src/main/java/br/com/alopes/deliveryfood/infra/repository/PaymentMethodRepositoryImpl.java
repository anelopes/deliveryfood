package br.com.alopes.deliveryfood.infra.repository;

import br.com.alopes.deliveryfood.domain.model.PaymentMethod;
import br.com.alopes.deliveryfood.domain.repository.PaymentMethodRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    @PersistenceContext //Injeta o EntityManager com algumas configurações inciais
    private EntityManager entityManager;

    @Override
    public List<PaymentMethod> findAll() {
        return entityManager.createQuery("from PaymentMethod", PaymentMethod.class).getResultList(); //JPQL -> consulta pelo objeto
    }

    @Override
    public PaymentMethod findById(Long id) {
        return entityManager.find(PaymentMethod.class, id);
    }

    @Transactional
    @Override
    public PaymentMethod save(PaymentMethod PaymentMethod) {
        return entityManager.merge(PaymentMethod);
    }

    @Transactional
    @Override
    public void delete(PaymentMethod PaymentMethod) {
        PaymentMethod = findById(PaymentMethod.getId());
        entityManager.remove(PaymentMethod);
    }
}
