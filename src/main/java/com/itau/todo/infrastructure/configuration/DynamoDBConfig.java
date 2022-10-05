package com.itau.todo.infrastructure.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.itau.todo.domain.entities.Quadro;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;

@Configuration
@EnableDynamoDBRepositories("com.itau.todo.infrastructure.repository.dynamoDb")
public class DynamoDBConfig {

    @Value("${aws.access.key.id:fakeId}")
    private String awsAccessKeyId;

    @Value("${aws.access.key.secret:fakeSecret}")
    private String awsAccessKeySecret;

    @Value("${dynamodb.service.endpoint:http://localhost:8000/}")
    private String dynamoDBServiceEndPoint;

    @Value("${dynamodb.service.region:sa-east-1}")
    private String dynamoDBRegion;

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration())
                .withCredentials(credentialsProvider())
                .build();
    }

    private AWSStaticCredentialsProvider credentialsProvider() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKeyId, awsAccessKeySecret)
        );
    }

    private AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(dynamoDBServiceEndPoint, dynamoDBRegion);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void setupBD(ApplicationReadyEvent event) {
//        AmazonDynamoDB amazonDynamoDB = event.getApplicationContext().getBean(AmazonDynamoDB.class);
//        DynamoDBMapper dynamoDBMapper = event.getApplicationContext().getBean(DynamoDBMapper.class);
//
//        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Quadro.class);
//
//        if (!amazonDynamoDB.listTables().getTableNames().contains(createTableRequest.getTableName())) {
//            createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
//            amazonDynamoDB.createTable(createTableRequest);
//        }
//    }

}
