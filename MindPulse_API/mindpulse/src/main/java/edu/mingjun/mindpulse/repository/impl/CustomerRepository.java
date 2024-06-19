package edu.mingjun.mindpulse.repository.impl;

import edu.mingjun.mindpulse.config.AwsDynamoDbClient;
import edu.mingjun.mindpulse.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.*;

import static java.lang.StringTemplate.STR;

@Slf4j
@Repository
public class CustomerRepository {

    private final String TABLE_NAME = "Customer";
    private final DynamoDbTable<Customer> CUSTOMER_TABLE;

    public CustomerRepository() {
        CUSTOMER_TABLE = AwsDynamoDbClient.getInstance().getDynamoDbEnhancedClient().table(TABLE_NAME, TableSchema.fromBean(Customer.class));
        this.initializeTable();
    }

    public void initializeTable() {
        try {
            AwsDynamoDbClient.getInstance().getDynamoDbClient().describeTable(DescribeTableRequest.builder().tableName(TABLE_NAME).build());
            log.info(STR."Table \{TABLE_NAME} found, no need to create table.");
        } catch (ResourceNotFoundException _) {
            CreateTableEnhancedRequest createTableRequest = CreateTableEnhancedRequest.builder()
                    .provisionedThroughput(ProvisionedThroughput.builder()
                            .readCapacityUnits(1L)
                            .writeCapacityUnits(1L)
                            .build())
                    .build();

            if (CUSTOMER_TABLE != null) {
                CUSTOMER_TABLE.createTable(createTableRequest);
                log.info(STR."Table not found, created table: \{TABLE_NAME}");
            } else {
                log.error(STR."Table not found, table not created because \{TABLE_NAME} is null");
            }
        } catch (DynamoDbException e) {
            log.error(STR."Table not found and failed to create table, error message: \{e.getMessage()}");
        }
    }

    public boolean save(Customer customer) {
        PutItemEnhancedRequest<Customer> request = PutItemEnhancedRequest.builder(Customer.class)
                .item(customer)
                .build();

        try {
            CUSTOMER_TABLE.putItem(request);
            log.info(STR."Successfully insert into \{TABLE_NAME}, data:\{customer.toString()}");
            return true;
        } catch (DynamoDbException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public Customer findByPartitionKey(String uuId) {
        try{
            QueryConditional query = QueryConditional.keyEqualTo(key -> key.partitionValue(uuId));

            Customer customer = this.CUSTOMER_TABLE.query(getQueryEnhancedRequest(query)).items().stream().findFirst().orElse(null);

            if(customer != null) {
                log.info(STR."Successfully find by partition key, data:\{customer.toString()}");
                return customer;
            }else{
                log.warn(STR."Customer not found, UUID: \{uuId}");
                return null;
            }

        }catch(DynamoDbException e){
            log.error(STR."Error occured when performing transaction findByPartitionKey(\"\{uuId}\"), \{e.getMessage()}");
            return null;
        }
    }

    public boolean update(Customer customer) {
        try{
            UpdateItemEnhancedRequest<Customer> updateRequest = UpdateItemEnhancedRequest.builder(Customer.class)
                    .item(customer)
                    .build();
            this.CUSTOMER_TABLE.updateItem(updateRequest);
            log.info(STR."Successfully updated into \{TABLE_NAME}, data after update:\{customer.toString()}");
            return true;
        } catch (DynamoDbException e) {
            log.error(STR."Error occurred when performing transaction update(\{customer.toString()}), \{e.getMessage()}");
            return false;
        }
    }

    public boolean deleteByPartitionKey(String uuId) {
        try{
            this.CUSTOMER_TABLE.deleteItem(request -> request.key(key -> key.partitionValue(uuId)));
            log.info(STR."Successfully deleted \{TABLE_NAME}, UUID:\{uuId}");
            return true;
        } catch (DynamoDbException e) {
            log.error(STR."Error occurred when performing transaction deleteByPartitionKey(\{uuId}), \{e.getMessage()}");
            return false;
        }
    }

    private static QueryEnhancedRequest getQueryEnhancedRequest(QueryConditional query) {
        return QueryEnhancedRequest.builder().queryConditional(query).build();
    }
}
