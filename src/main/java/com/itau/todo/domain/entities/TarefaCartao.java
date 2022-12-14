package com.itau.todo.domain.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.itau.todo.infrastructure.util.autogenerate.LocalDateTimeAutoGenerate;
import com.itau.todo.infrastructure.util.converter.LocalDateTimeToStringConverter;
import lombok.Data;

import java.time.LocalDateTime;

@DynamoDBTable(tableName = "quadros")
@Data
public class TarefaCartao {
    @DynamoDBHashKey
    private String id;
    @DynamoDBRangeKey
    private String tipo;
    private String titulo;
    private String descricao;
    @DynamoDBTypeConvertedEnum
    private Prioridade prioridade;
    @DynamoDBTypeConverted(converter = LocalDateTimeToStringConverter.class)
    @DynamoDBAutoGenerated(generator = LocalDateTimeAutoGenerate.class)
    private LocalDateTime criacao;
    @DynamoDBTypeConverted(converter = LocalDateTimeToStringConverter.class)
    private LocalDateTime previsao;
    @DynamoDBTypeConverted(converter = LocalDateTimeToStringConverter.class)
    private LocalDateTime conclusao;
}
