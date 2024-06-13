package main.java.edu.mingjun.mindpulse.repository;

import main.java.edu.mingjun.mindpulse.model.Customer;

import lombok.extern.slf4j.Slf4j;
import lombok.NonNull;

import org.springframework.stereotype.Repository;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.core.internal.waiters.ResponseOrException;

import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
@Repository
public class CustomerRepository {
    private final TableSchema<Customer> CUSTOMER_TABLE_SCHEMA = TableSchema.fromBean(Customer.class);
    private final DynamoDbTable<Customer> CUSTOMER_TABLE;

    @Autowired
    public CustomerRepository(DynamoDbClient dynamoDbClient, DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.CUSTOMER_TABLE = dynamoDbEnhancedClient.table(Customer.class.getSimpleName().toString(), this.CUSTOMER_TABLE_SCHEMA);
        this.checkTableExists(dynamoDbClient);
    }

    private void createTable (DynamoDbClient dynamoDbClient) {
        this.CUSTOMER_TABLE.createTable(builder -> builder
            .provisionedThroughput(b -> b
            .readCapacityUnits(5L)
            .writeCapacityUnits(5L)
            .build())
        );
        
        try (DynamoDbWaiter waiter = DynamoDbWaiter.builder().client(dynamoDbClient).build()) {
            ResponseOrException<DescribeTableResponse> response = waiter
                .waitUntilTableExists(builder -> builder.tableName(Customer.class.getSimpleName()).build())
                .matched();
            DescribeTableResponse tableDescription = response.response().orElseThrow(
                () -> new RuntimeException("Table do not exist, " + Customer.class.getSimpleName() + " failed to create."));
            log.info("Table do not exists, " + Customer.class.getSimpleName().toString() + " successfully created.");
        }
    }

    private void checkTableExists(DynamoDbClient dynamoDbClient) {
        try {
            dynamoDbClient.describeTable(DescribeTableRequest.builder().tableName(Customer.class.getSimpleName()).build());
        } catch (ResourceNotFoundException e) {
            createTable(dynamoDbClient);
        }
    }

    @NonNull
    public void save(Customer customer) {
        CUSTOMER_TABLE.putItem(customer);
    }

    @NonNull
    public void update(Customer customer) {
        CUSTOMER_TABLE.updateItem(customer);
    }
}
