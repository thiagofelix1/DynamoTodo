package com.itau.todo.infrastructure.repository.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@EnableScan
public class DynamoDbCrud<T> {

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

    // TODO: verificar se manteremos ou não
    public T getById(String id) {
        return dynamoDBMapper.load(typeParameterClass, id);
    }

    public List<T> findAll() {
        return new ArrayList<>();
    }

    public void update(String id, T t) {
        dynamoDBMapper.save(t, new DynamoDBSaveExpression()
                .withExpectedEntry("id", new ExpectedAttributeValue(
                        new AttributeValue().withS(id))));
    }

    // TODO: verificar se manteremos ou não 2
    public void delete(String id) {
        // TODO: getById quebrando o metodo (DynamoDBMappingException: Quadro[tipo]; no RANGE key value present)
        T item = getById(id);
        dynamoDBMapper.delete(item, new DynamoDBDeleteExpression()
                .withExpectedEntry("id", new ExpectedAttributeValue(
                        new AttributeValue().withS(id))));
    }

}
