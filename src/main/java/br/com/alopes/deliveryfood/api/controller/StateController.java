package br.com.alopes.deliveryfood.api.controller;

import br.com.alopes.deliveryfood.domain.exception.EntityInUseException;
import br.com.alopes.deliveryfood.domain.exception.EntityNotFoundException;
import br.com.alopes.deliveryfood.domain.model.State;
import br.com.alopes.deliveryfood.domain.service.RegisterStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private RegisterStateService registerStateService;

    @GetMapping
    public List<State> list() {
        return registerStateService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> findById(@PathVariable Long id) {
        try {
            State state = registerStateService.findById(id);
            return ResponseEntity.ok(state);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State save(@RequestBody State state) {
        return registerStateService.save(state);
    }

    @PutMapping("/{id}")
    public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state) {
        try {
            State stateUpdated = registerStateService.update(id, state);
            return ResponseEntity.ok(stateUpdated);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<State> delete(@PathVariable Long id) {
        try {
            registerStateService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
