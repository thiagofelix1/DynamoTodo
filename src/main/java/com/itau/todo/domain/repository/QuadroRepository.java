package com.itau.todo.domain.repository;

import com.itau.todo.domain.entities.Quadro;

import java.util.List;
import java.util.Optional;

public interface QuadroRepository {

    Quadro save(Quadro quadro);

    Optional<Quadro> getByHashKeyAndRangeKey(String hashKey, String rangeKey);

    List<Quadro> findAll();

    void update(String id, Quadro quadro);

    void delete(String hashKey, String rangeKey);

}
