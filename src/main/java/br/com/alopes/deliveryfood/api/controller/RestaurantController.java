package br.com.alopes.deliveryfood.api.controller;

import br.com.alopes.deliveryfood.domain.exception.EntityInUseException;
import br.com.alopes.deliveryfood.domain.exception.EntityNotFoundException;
import br.com.alopes.deliveryfood.domain.exception.KitchenNotFoundException;
import br.com.alopes.deliveryfood.domain.exception.RestaurantNotFoundException;
import br.com.alopes.deliveryfood.domain.model.Restaurant;
import br.com.alopes.deliveryfood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RegisterRestaurantService registerRestaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return registerRestaurantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        try {
            Restaurant restaurant = registerRestaurantService.findById(id);
            return ResponseEntity.ok(restaurant);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            restaurant = registerRestaurantService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        try {
            restaurant = registerRestaurantService.update(id, restaurant);
            return ResponseEntity.ok(restaurant);
        } catch (RestaurantNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (KitchenNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            registerRestaurantService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> restaurantInput) {
        try {
            Restaurant restaurant = registerRestaurantService.partialUpdate(id, restaurantInput);
            return ResponseEntity.ok(restaurant);
        } catch (RestaurantNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (KitchenNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
