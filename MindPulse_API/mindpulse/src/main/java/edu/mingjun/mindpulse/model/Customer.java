package edu.mingjun.mindpulse.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
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
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String address;
    private long nricNo;

    @DynamoDbPartitionKey
    public String getUuId() {
        return uuId;
    }

    public void setId(String uuId) {
        this.uuId = uuId;
    }

    @DynamoDbAttribute("firstName")
    public String getFirstName() {
        return firstName;
    }

    @DynamoDbAttribute("lastName")
    public String getLastName() {
        return lastName;
    }

    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    @DynamoDbAttribute("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    @DynamoDbAttribute("address")
    public String getAddress() {
        return address;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("nricNo")
    public long getNricNo() {
        return nricNo;
    }
}


