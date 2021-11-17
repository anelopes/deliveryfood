package br.com.alopes.deliveryfood.domain.service;

import br.com.alopes.deliveryfood.domain.exception.CityNotFoundException;
import br.com.alopes.deliveryfood.domain.exception.EntityInUseException;
import br.com.alopes.deliveryfood.domain.exception.EntityNotFoundException;
import br.com.alopes.deliveryfood.domain.exception.StateNotFoundException;
import br.com.alopes.deliveryfood.domain.model.City;
import br.com.alopes.deliveryfood.domain.model.State;
import br.com.alopes.deliveryfood.domain.repository.CityRepository;
import br.com.alopes.deliveryfood.domain.repository.StateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterCityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city) {
        Long kitchenId = city.getState().getId();
        State kitchen = stateRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não existe um cadastro de estado com código %d", kitchenId)));
        city.setState(kitchen);
        return cityRepository.save(city);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não existe um cadastro de cidade com código %d", id)));
    }

    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de cidade com código %d", id));
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(String.format("Cidade de código %d não pode ser removida pois está em uso", id));
        }
    }

    public City update(Long id, City city) {
        City cityFound = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(String.format("Não existe um cadastro de cidade com código %d", id)));

        Long kitchenId = city.getState().getId();
        State kitchen = stateRepository.findById(kitchenId)
                .orElseThrow(() -> new StateNotFoundException(String.format("Não existe um cadastro de estado com código %d", kitchenId)));
        city.setState(kitchen);

        BeanUtils.copyProperties(city, cityFound, "id");
        return cityRepository.save(cityFound);
    }
}
