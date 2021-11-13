package br.com.alopes.deliveryfood.infra.repository;

import br.com.alopes.deliveryfood.domain.model.PaymentMethod;
import br.com.alopes.deliveryfood.domain.model.Permission;
import br.com.alopes.deliveryfood.domain.repository.PermissionRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @PersistenceContext //Injeta o EntityManager com algumas configurações inciais
    private EntityManager entityManager;

    @Override
    public List<Permission> findAll() {
        return entityManager.createQuery("from Permission", Permission.class).getResultList(); //JPQL -> consulta pelo objeto
    }

    @Override
    public Permission findById(Long id) {
        return entityManager.find(Permission.class, id);
    }

    @Transactional
    @Override
    public Permission save(Permission permission) {
        return entityManager.merge(permission);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Permission permission = findById(id);

        if (permission == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(permission);
    }
}
