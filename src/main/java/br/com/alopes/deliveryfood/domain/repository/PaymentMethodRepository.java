package br.com.alopes.deliveryfood.domain.repository;

import br.com.alopes.deliveryfood.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> findAll();

    PaymentMethod findById(Long id);

    PaymentMethod save(PaymentMethod kitchen);

    void delete(PaymentMethod kitchen);

}
