package edu.mingjun.mindpulse.model.therapist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@EqualsAndHashCode(callSuper = true)
@Data
@DynamoDbBean
public class TherapistPerson extends Therapist {
    private String name;

    public TherapistPerson(String uuId, String name) {
        super.setTherapistUuId(uuId);
        super.setRecordType("TherapistPerson");
        this.setName(name);
    }

    @Override
    @DynamoDbPartitionKey
    public String getTherapistUuId() {return super.getTherapistUuId();}

    @Override
    @DynamoDbSortKey
    public String getRecordType() {return super.getRecordType();}
}
