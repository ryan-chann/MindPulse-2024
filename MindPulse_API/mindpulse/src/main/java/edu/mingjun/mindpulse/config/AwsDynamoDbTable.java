package edu.mingjun.mindpulse.config;

import edu.mingjun.mindpulse.global.AwsDynamoDb;
import edu.mingjun.mindpulse.model.*;
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
    private DynamoDbTable<TherapistLanguage> therapistLanguageTable;
    private DynamoDbTable<TherapistModeOfConduct> therapistModeOfConductTable;
    private DynamoDbTable<TherapistEducation> therapistEducationTable;
    private DynamoDbTable<TherapistAssistance> therapistAssistanceTable;
    private DynamoDbTable<TherapistApproach> therapistApproachTable;

    private AwsDynamoDbTable() {
        try{
            AwsDynamoDbClient.getInstance().getDynamoDbClient().describeTable(DescribeTableRequest.builder().tableName(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME).build());
            this.setTherapistInfoTable(AwsDynamoDbClient.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistInfo.class)));
            this.setTherapistLanguageTable(AwsDynamoDbClient.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistLanguage.class)));
            this.setTherapistModeOfConductTable(AwsDynamoDbClient.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistModeOfConduct.class)));
            this.setTherapistEducationTable(AwsDynamoDbClient.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistEducation.class)));
            this.setTherapistAssistanceTable(AwsDynamoDbClient.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistAssistance.class)));
            this.setTherapistApproachTable(AwsDynamoDbClient.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistApproach.class)));
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
