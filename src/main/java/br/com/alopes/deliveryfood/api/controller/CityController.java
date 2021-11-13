package br.com.alopes.deliveryfood.api.controller;

import br.com.alopes.deliveryfood.domain.exception.CityNotFoundException;
import br.com.alopes.deliveryfood.domain.exception.EntityInUseException;
import br.com.alopes.deliveryfood.domain.exception.EntityNotFoundException;
import br.com.alopes.deliveryfood.domain.exception.StateNotFoundException;
import br.com.alopes.deliveryfood.domain.model.City;
import br.com.alopes.deliveryfood.domain.service.RegisterCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private RegisterCityService registerCityService;

    @GetMapping
    public List<City> list() {
        return registerCityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        try {
            City city = registerCityService.findById(id);
            return ResponseEntity.ok(city);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody City city) {
        try {
            city = registerCityService.save(city);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(city);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody City city) {
        try {
            city = registerCityService.update(id, city);
            return ResponseEntity.ok(city);
        } catch (CityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (StateNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> delete(@PathVariable Long id) {
        try {
            registerCityService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
