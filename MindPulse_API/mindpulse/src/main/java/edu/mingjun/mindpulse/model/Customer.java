package edu.mingjun.mindpulse.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;



@Data
@Builder
@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor 
public class Customer {
    
    @Getter(onMethod_ = {@DynamoDbPartitionKey})
    @Builder.Default
    private String uuId = UUID.randomUUID().toString();
    private String fullName;
    private String emailAddress;
    private Integer phoneNo;
    @Getter(onMethod_ = {@DynamoDbSortKey})
    private Integer nricNo;
}
