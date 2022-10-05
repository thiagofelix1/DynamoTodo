package com.itau.todo.application.rest;

import com.itau.todo.application.request.QuadroRequest;
import com.itau.todo.application.response.QuadroResponse;
import com.itau.todo.domain.entities.Quadro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quadro")
public class QuadroController {

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid QuadroRequest quadroRequest){
        Quadro quadro = new Quadro(quadroRequest.getName(), quadroRequest.getTipo());

        return ResponseEntity.status(HttpStatus.CREATED).body(quadro);
    }
}
