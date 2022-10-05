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

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid QuadroRequest quadroRequest) {
        Quadro quadro = new Quadro(quadroRequest.getName(), quadroRequest.getTipo());
        quadroRepository.save(quadro);

        return ResponseEntity.status(HttpStatus.CREATED).body(quadro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagar(@PathVariable String id) {
        quadroRepository.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
