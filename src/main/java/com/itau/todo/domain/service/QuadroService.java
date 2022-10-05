package com.itau.todo.domain.service;

import com.itau.todo.domain.entities.Quadro;
import com.itau.todo.domain.repository.QuadroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuadroService {

    private final QuadroRepository quadroRepository;

    public Quadro criarQuadro(Quadro quadro) {
        return quadroRepository.save(quadro);
    }
}
