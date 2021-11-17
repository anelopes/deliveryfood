package br.com.alopes.deliveryfood.domain.repository;

import br.com.alopes.deliveryfood.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
