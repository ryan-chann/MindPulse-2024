package edu.mingjun.mindpulse.repository.impl;

import edu.mingjun.mindpulse.config.DynamoDbClientManager;
import edu.mingjun.mindpulse.model.Customer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.model.*;

@NoArgsConstructor
@Repository
public class CustomerRepository {

    private final String TABLE_NAME = "Customer";
    private final DynamoDbClientManager DYNAMODB_CLIENT_MANAGER = DynamoDbClientManager.getInstance();

    public String initializeTable() {
        try {
            this.DYNAMODB_CLIENT_MANAGER.getDynamoDbClient().describeTable(DescribeTableRequest.builder().tableName(TABLE_NAME).build());
            return "Table " + TABLE_NAME + " found, no need to create table.";
        } catch (ResourceNotFoundException _) {
            CreateTableRequest request = CreateTableRequest.builder()
                    .attributeDefinitions(
                            AttributeDefinition.builder()
                                    .attributeName("uuId")
                                    .attributeType(ScalarAttributeType.S)
                                    .build(),
                            AttributeDefinition.builder()
                                    .attributeName("nricNo")
                                    .attributeType(ScalarAttributeType.N)
                                    .build())
                    .keySchema(
                            KeySchemaElement.builder()
                                    .attributeName("uuId")
                                    .keyType(KeyType.HASH)
                                    .build(),
                            KeySchemaElement.builder()
                                    .attributeName("nricNo")
                                    .keyType(KeyType.RANGE)
                                    .build())
                    .provisionedThroughput(
                            ProvisionedThroughput.builder()
                                    .readCapacityUnits(1L)
                                    .writeCapacityUnits(1L)
                                    .build())
                    .tableName(TABLE_NAME)
                    .build();
            this.DYNAMODB_CLIENT_MANAGER.getDynamoDbClient().createTable(request);
            return "Table not found, created table: " + TABLE_NAME;
        } catch (DynamoDbException e) {
            return "Table not found and failed to create table, error message: " + e.getMessage();
        }
    }
//
//    public String save(Customer customer) {
//
//    }
}
