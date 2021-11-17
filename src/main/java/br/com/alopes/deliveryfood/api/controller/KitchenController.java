package br.com.alopes.deliveryfood.api.controller;

import br.com.alopes.deliveryfood.domain.exception.EntityInUseException;
import br.com.alopes.deliveryfood.domain.exception.EntityNotFoundException;
import br.com.alopes.deliveryfood.domain.model.Kitchen;
import br.com.alopes.deliveryfood.domain.service.RegisterKitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private RegisterKitchenService registerKitchenService;

    @GetMapping
    public List<Kitchen> list() {
        return registerKitchenService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
        try {
            Kitchen kitchen = registerKitchenService.findById(id);
            return ResponseEntity.ok(kitchen);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody Kitchen kitchen) {
        return registerKitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        try {
            Kitchen kitchenUpdated = registerKitchenService.update(id, kitchen);
            return ResponseEntity.ok(kitchenUpdated);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            registerKitchenService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
