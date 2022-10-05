package com.itau.todo.domain.service;

import com.itau.todo.domain.entities.Quadro;
import com.itau.todo.domain.repository.QuadroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuadroService {

    @Autowired
    private QuadroRepository quadroRepository;

    public Quadro criarQuadro(Quadro quadro) {
        return quadroRepository.save(quadro);
    }
}
