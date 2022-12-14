package com.itau.todo.infrastructure.repository.dynamo;

import com.itau.todo.domain.entities.Quadro;
import com.itau.todo.domain.repository.QuadroRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDbQuadroRepository extends DynamoDbCrud<Quadro> implements QuadroRepository {

    public DynamoDbQuadroRepository() { super(Quadro.class, "QUADRO"); }

}
