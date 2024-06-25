package edu.mingjun.mindpulse.model.therapist;

import lombok.Data;
import lombok.EqualsAndHashCode;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@EqualsAndHashCode(callSuper = true)
@Data
@DynamoDbBean
public class ClinicalPsychologist extends Therapist {
    private String clinicalPsychologyId;

    public ClinicalPsychologist(String uuId, String clinicalPsychologyId) {
        super.setTherapistUuId(uuId);
        super.setRecordType("TherapistType#ClinicalPsychologist");
        this.setClinicalPsychologyId(clinicalPsychologyId);
    }

    @Override
    @DynamoDbPartitionKey
    public String getTherapistUuId() {return super.getTherapistUuId();}

    @Override
    @DynamoDbSortKey
    public String getRecordType() {return super.getRecordType();}
}
