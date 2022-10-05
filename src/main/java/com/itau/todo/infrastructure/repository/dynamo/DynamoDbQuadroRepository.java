package com.itau.todo.infrastructure.repository.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.itau.todo.domain.entities.Quadro;
import com.itau.todo.domain.repository.QuadroRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDbQuadroRepository extends DynamoDbCrud<Quadro> implements QuadroRepository {

    public DynamoDbQuadroRepository() { super(Quadro.class); }

    public Quadro getByHashKeyAndRangeKey(String hashKey, String sortKey) {
        return dynamoDBMapper.load(typeParameterClass, hashKey, sortKey);
    }

    public void delete(String hashKey, String rangeKey) {
        // TODO: Arrumar pois quando vem nulo dá nullpointer
        Quadro item = getByHashKeyAndRangeKey(hashKey, rangeKey);
        dynamoDBMapper.delete(item, new DynamoDBDeleteExpression()
                .withExpectedEntry("id", new ExpectedAttributeValue(
                        new AttributeValue().withS(hashKey)))
                .withExpectedEntry("tipo", new ExpectedAttributeValue(
                        new AttributeValue().withS(rangeKey)
                )));
    }

}
