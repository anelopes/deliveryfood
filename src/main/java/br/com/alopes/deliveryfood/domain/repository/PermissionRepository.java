package br.com.alopes.deliveryfood.domain.repository;

import br.com.alopes.deliveryfood.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> findAll();

    Permission findById(Long id);

    Permission save(Permission permission);

    void delete(Long id);

}
