package br.com.alopes.deliveryfood.domain.service;

import br.com.alopes.deliveryfood.domain.exception.EntityInUseException;
import br.com.alopes.deliveryfood.domain.exception.EntityNotFoundException;
import br.com.alopes.deliveryfood.domain.model.State;
import br.com.alopes.deliveryfood.domain.repository.StateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterStateService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public State findById(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não existe um cadastro de estado com código %d", id)));
    }

    public void delete(Long id) {
        try {
            stateRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de estado com código %d", id));
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(String.format("Estado de código %d não pode ser removida pois está em uso", id));
        }
    }

    public State update(Long id, State state) {
        State stateFound = stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não existe um cadastro de estado com código %d", id)));
        BeanUtils.copyProperties(state, stateFound, "id");
        return stateRepository.save(stateFound);
    }
}
