package edu.mingjun.mindpulse.repository;

import edu.mingjun.mindpulse.model.Customer;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.core.internal.waiters.ResponseOrException;

@Slf4j
@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    private final TableSchema<Customer> CUSTOMER_TABLE_SCHEMA = TableSchema.fromBean(Customer.class);
    private final DynamoDbTable<Customer> CUSTOMER_TABLE;

    public CustomerRepositoryImpl() {
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
            .region(Region.AP_SOUTHEAST_3)
            .build();

        DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build();

        this.CUSTOMER_TABLE = dynamoDbEnhancedClient.table(Customer.class.getSimpleName(), CUSTOMER_TABLE_SCHEMA);

        try {
            dynamoDbClient.describeTable(DescribeTableRequest.builder().tableName(Customer.class.getSimpleName()).build());
            log.info("Table " + Customer.class.getSimpleName() + " already exists.");
        } catch (ResourceNotFoundException _) {
            this.CUSTOMER_TABLE.createTable(builder -> builder
                .provisionedThroughput(b -> b
                .readCapacityUnits(5L)
                .writeCapacityUnits(5L)
                .build())
            );

            log.info("Table " + Customer.class.getSimpleName() + " successfully created.");
            
            // Wait for table to become active
            try (DynamoDbWaiter waiter = dynamoDbClient.waiter()) {
                waiter.waitUntilTableExists(builder -> builder.tableName(Customer.class.getSimpleName()).build());
                log.info("Table " + Customer.class.getSimpleName()  + " is now active.");
            } catch (Exception _) {
                log.error("Failed to create table " + Customer.class.getSimpleName()  + ".");
            }
        }
    }

    @Override
    @NonNull
    public void save(Customer customer) {
        CUSTOMER_TABLE.putItem(customer);
    }

    @Override
    @NonNull
    public Customer get(String uuId) {
        return CUSTOMER_TABLE.getItem(request -> request.key(keyBuilder -> keyBuilder.partitionValue(uuId)));
    }

    @Override
    @NonNull
    public void update(Customer customer) {
        CUSTOMER_TABLE.updateItem(customer);
    }

    @Override
    @NonNull
    public void delete(String uuId) {
        CUSTOMER_TABLE.deleteItem(request -> request.key(keyBuilder -> keyBuilder.partitionValue(uuId)));
    }
}
