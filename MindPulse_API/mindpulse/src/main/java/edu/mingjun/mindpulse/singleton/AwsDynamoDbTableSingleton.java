package edu.mingjun.mindpulse.singleton;

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
public class AwsDynamoDbTableSingleton {
    private static AwsDynamoDbTableSingleton awsDynamoDbTableSingleton = null;

    private DynamoDbTable<TherapistInfo> therapistInfoTable;
    private DynamoDbTable<TherapistLanguage> therapistLanguageTable;
    private DynamoDbTable<TherapistModeOfConduct> therapistModeOfConductTable;
    private DynamoDbTable<TherapistEducation> therapistEducationTable;
    private DynamoDbTable<TherapistAssistance> therapistAssistanceTable;
    private DynamoDbTable<TherapistApproach> therapistApproachTable;
    private DynamoDbTable<ServiceOffered> serviceOfferedTable;

    private AwsDynamoDbTableSingleton() {
        try{
            AwsDynamoDbClientSingleton.getInstance().getDynamoDbClient().describeTable(DescribeTableRequest.builder().tableName(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME).build());
            this.setTherapistInfoTable(AwsDynamoDbClientSingleton.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistInfo.class)));
            this.setTherapistLanguageTable(AwsDynamoDbClientSingleton.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistLanguage.class)));
            this.setTherapistModeOfConductTable(AwsDynamoDbClientSingleton.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistModeOfConduct.class)));
            this.setTherapistEducationTable(AwsDynamoDbClientSingleton.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistEducation.class)));
            this.setTherapistAssistanceTable(AwsDynamoDbClientSingleton.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistAssistance.class)));
            this.setTherapistApproachTable(AwsDynamoDbClientSingleton.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(TherapistApproach.class)));
            this.setServiceOfferedTable(AwsDynamoDbClientSingleton.getInstance().getDynamoDbEnhancedClient().table(AwsDynamoDb.AWS_DYNAMODB_TABLE_NAME, TableSchema.fromBean(ServiceOffered.class)));
        } catch (DynamoDbException e){
            log.error("Mindpulse table not found, Please create table before running application.");
            System.exit(1);
        }
    }

    public static AwsDynamoDbTableSingleton getInstance() {
        if (awsDynamoDbTableSingleton == null) {
            awsDynamoDbTableSingleton = new AwsDynamoDbTableSingleton();
        }
        return awsDynamoDbTableSingleton;
    }
}
