package com.itau.todo.application.rest;

import com.itau.todo.application.request.QuadroRequest;
import com.itau.todo.domain.entities.Quadro;
import com.itau.todo.domain.repository.QuadroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quadro")
public class QuadroController {

    private final QuadroRepository quadroRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(quadroRepository.findAll());
    }

    @GetMapping("/{hashKey}")
    public ResponseEntity<?> getPorId(@PathVariable String hashKey) {
        // TODO: fazer de verdade
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid QuadroRequest quadroRequest) {
        Quadro quadro = new Quadro(quadroRequest.getTipo(), quadroRequest.getName());
        quadroRepository.save(quadro);

        return ResponseEntity.status(HttpStatus.CREATED).body(quadro);
    }

    @PutMapping("{}")
    public ResponseEntity<?> atualizar() {
        // TODO: fazer de verdade2
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{hashKey}/{rangeKey}")
    public ResponseEntity<?> apagar(@PathVariable String hashKey, @PathVariable String rangeKey) {
        quadroRepository.delete(hashKey, rangeKey);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
