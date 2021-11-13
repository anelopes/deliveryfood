package br.com.alopes.deliveryfood.domain.exception;

public class KitchenNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public KitchenNotFoundException(String message) {
        super(message);
    }
}
