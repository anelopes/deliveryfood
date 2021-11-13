package br.com.alopes.deliveryfood.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CityNotFoundException(String message) {
        super(message);
    }
}
