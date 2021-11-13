package br.com.alopes.deliveryfood.domain.service;

import br.com.alopes.deliveryfood.domain.exception.EntityInUseException;
import br.com.alopes.deliveryfood.domain.exception.EntityNotFoundException;
import br.com.alopes.deliveryfood.domain.model.Kitchen;
import br.com.alopes.deliveryfood.domain.repository.KitchenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterKitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public List<Kitchen> findAll() {
        return kitchenRepository.findAll();
    }

    public Kitchen findById(Long id) {
        Kitchen kitchen = kitchenRepository.findById(id);
        if (kitchen == null) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de cozinha com código %d", id));
        }
        return kitchen;
    }

    public void delete(Long id) {
        try {
            kitchenRepository.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de cozinha com código %d", id));
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(String.format("Cozinha de código %d não pode ser removida pois está em uso", id));
        }
    }

    public Kitchen update(Long id, Kitchen kitchen) {
        Kitchen kitchenFound = kitchenRepository.findById(id);
        if (kitchenFound == null) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de cozinha com código %d", id));
        }
        BeanUtils.copyProperties(kitchen, kitchenFound, "id");
        kitchenRepository.save(kitchenFound);
        return kitchenFound;
    }
}
