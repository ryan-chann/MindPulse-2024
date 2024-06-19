package edu.mingjun.mindpulse.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDbBean
public class Customer {
    private String uuId;
    private String fullName;
    private String email;
    private String phoneNo;
    private long nricNo;

    @DynamoDbPartitionKey
    public String getUuId() {
        return uuId;
    }

    @DynamoDbAttribute("fullName")
    public String getFullName() {return fullName;}

    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    @DynamoDbAttribute("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    @DynamoDbAttribute("nricNo")
    public long getNricNo() {
        return nricNo;
    }
}


