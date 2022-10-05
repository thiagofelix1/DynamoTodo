package com.itau.todo.domain.repository;

import com.itau.todo.domain.entities.Quadro;

import java.util.List;

public interface QuadroRepository {

    Quadro save(Quadro quadro);

    Quadro getById(String id);

    List<Quadro> findAll();

    void update(String id, Quadro quadro);

    void delete(String id);
}
