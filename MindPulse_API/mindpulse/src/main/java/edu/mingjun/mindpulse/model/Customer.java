package main.java.edu.mingjun.mindpulse.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import lombok.Data;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Data
@Builder
@DynamoDbBean
public class Customer {
    @Getter(onMethod_ = {@DynamoDbPartitionKey})
    @Builder.Default
    private String uuId = UUID.randomUUID().toString();
    private String fullName;
    private String emailAddress;
    private int phoneNo;
    @Getter(onMethod_ = {@DynamoDbSortKey})
    private int nricNo;
}
