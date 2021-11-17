package br.com.alopes.deliveryfood.domain.service;

import br.com.alopes.deliveryfood.domain.exception.EntityInUseException;
import br.com.alopes.deliveryfood.domain.exception.EntityNotFoundException;
import br.com.alopes.deliveryfood.domain.exception.KitchenNotFoundException;
import br.com.alopes.deliveryfood.domain.exception.RestaurantNotFoundException;
import br.com.alopes.deliveryfood.domain.model.Kitchen;
import br.com.alopes.deliveryfood.domain.model.Restaurant;
import br.com.alopes.deliveryfood.domain.repository.KitchenRepository;
import br.com.alopes.deliveryfood.domain.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class RegisterRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não existe um cadastro de cozinha com código %d", kitchenId)));
        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não existe um cadastro de restaurante com código %d", id)));
    }

    public void delete(Long id) {
        try {
            restaurantRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de restaurante com código %d", id));
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException(String.format("Restaurante de código %d não pode ser removida pois está em uso", id));
        }
    }

    public Restaurant update(Long id, Restaurant restaurant) {
        Restaurant restaurantFound = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(String.format("Não existe um cadastro de restaurante com código %d", id)));

        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(String.format("Não existe um cadastro de cozinha com código %d", kitchenId)));
        restaurant.setKitchen(kitchen);

        BeanUtils.copyProperties(restaurant, restaurantFound, "id");
        return restaurantRepository.save(restaurantFound);
    }

    public Restaurant partialUpdate(Long id, Map<String, Object> restaurant) {
        Restaurant restaurantFound = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(String.format("Não existe um cadastro de restaurante com código %d", id)));

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurantInput = objectMapper.convertValue(restaurant, Restaurant.class);

        restaurant.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, key);
            field.setAccessible(true); //para acessar atributo privado

            Object newValue = ReflectionUtils.getField(field, restaurantInput);//pega no objeto de entrada para os tipos do campos já estarem corretos
            ReflectionUtils.setField(field, restaurantFound, newValue);
        });

        return update(id, restaurantFound);
    }
}
