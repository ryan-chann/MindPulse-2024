package edu.mingjun.mindpulse.config;

import edu.mingjun.mindpulse.global.AwsDynamoDb;
import edu.mingjun.mindpulse.model.TherapistInfo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Slf4j
@Getter
@Setter
public class AwsDynamoDbTable {
    private static AwsDynamoDbTable awsDynamoDbTableSingleton = null;

    private DynamoDbTable<TherapistInfo> therapistInfoTable;

    private AwsDynamoDbTable() {
        try{
            AwsDynamoDbClient.getInstance().getDynamoDbClient().describeTable(DescribeTableRequest.builder().tableName(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME).build());
            this.setTherapistInfoTable(AwsDynamoDbClient.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistInfo.class)));

        } catch (DynamoDbException e){
            log.error("Mindpulse table not found, Please create table before running application.");
            System.exit(1);
        }
    }


    public static AwsDynamoDbTable getInstance() {
        if (awsDynamoDbTableSingleton == null) {
            awsDynamoDbTableSingleton = new AwsDynamoDbTable();
        }
        return awsDynamoDbTableSingleton;
    }
}
