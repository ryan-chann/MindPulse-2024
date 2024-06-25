package edu.mingjun.mindpulse.model.therapist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@DynamoDbBean
public class TherapistApproaches extends Therapist{
    private List<String> approaches;

    public TherapistApproaches(String uuId) {
        super.setTherapistUuId(uuId);
        super.setRecordType("TherapistApproaches");
    }

    @Override
    @DynamoDbPartitionKey
    public String getTherapistUuId() {return super.getTherapistUuId();}

    @Override
    @DynamoDbSortKey
    public String getRecordType() {return super.getRecordType();}
}
