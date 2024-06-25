package edu.mingjun.mindpulse.model.therapist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@DynamoDbBean
public class Therapist {
    private String therapistUuId;
    private String recordType;

    @DynamoDbPartitionKey
    public String getTherapistUuId() {return this.therapistUuId;}

    @DynamoDbSortKey
    public String getRecordType() {return this.recordType;}
}
