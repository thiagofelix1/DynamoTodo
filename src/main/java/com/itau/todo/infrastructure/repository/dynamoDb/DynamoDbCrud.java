package com.itau.todo.infrastructure.repository.dynamoDb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.itau.todo.domain.entities.Quadro;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@EnableScan
public class DynamoDbCrud<T>{
    @Autowired
    DynamoDBMapper dynamoDBMapper;

    final Class<T> typeParameterClass;

    public DynamoDbCrud(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public T save(T t) {
        dynamoDBMapper.save(t);
        return t;
    }

    public T getById(String id) {
        return dynamoDBMapper.load(typeParameterClass, id);
    }

    public List<T> findAll() {
        return null;
    }

    public void update(String id, T t) {
        dynamoDBMapper.save(t,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(id)
                                )));
    }


}
