package com.itau.todo.infrastructure.repository.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@EnableScan
public abstract class DynamoDbCrud<T> {

    @Autowired
    DynamoDBMapper dynamoDBMapper;

    final Class<T> typeParameterClass;

    private final String rangeKey;

    protected DynamoDbCrud(Class<T> typeParameterClass, String rangeKey) {
        this.typeParameterClass = typeParameterClass;
        this.rangeKey = rangeKey;
    }

    public T save(T t) {
        dynamoDBMapper.save(t);
        return t;
    }

    public Optional<T> getByHashKeyAndRangeKey(String hashKey, String sortKey) {
        return Optional.ofNullable(dynamoDBMapper.load(typeParameterClass, hashKey, sortKey));
    }

    public List<T> findAll() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue().withS(rangeKey));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.scan(typeParameterClass, scanExpression);
    }

    public void update(String id, T t) {
        // TODO: testar
        dynamoDBMapper.save(t, new DynamoDBSaveExpression()
                .withExpectedEntry("id", new ExpectedAttributeValue(
                        new AttributeValue().withS(id))));
    }

    public void delete(String hashKey, String rangeKey) {
        Optional<T> item = getByHashKeyAndRangeKey(hashKey, rangeKey);

        if(item.isEmpty()) {
            throw new RuntimeException("Item nao existente");
        }

        dynamoDBMapper.delete(item.get(), new DynamoDBDeleteExpression()
                .withExpectedEntry("id", new ExpectedAttributeValue(
                        new AttributeValue().withS(hashKey)))
                .withExpectedEntry("tipo", new ExpectedAttributeValue(
                        new AttributeValue().withS(rangeKey)
                )));
    }

}
