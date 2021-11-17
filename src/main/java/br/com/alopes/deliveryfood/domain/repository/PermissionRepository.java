package br.com.alopes.deliveryfood.domain.repository;

import br.com.alopes.deliveryfood.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
