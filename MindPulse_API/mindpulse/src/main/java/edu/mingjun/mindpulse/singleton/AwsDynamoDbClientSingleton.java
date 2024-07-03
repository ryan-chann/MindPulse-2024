package edu.mingjun.mindpulse.singleton;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AwsDynamoDbClientSingleton {
    private static AwsDynamoDbClientSingleton awsDynamoDbClientSingleton = null;

    private DynamoDbClient dynamoDbClient;
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public static AwsDynamoDbClientSingleton getInstance() {
        if (awsDynamoDbClientSingleton == null) {
            awsDynamoDbClientSingleton = new AwsDynamoDbClientSingleton();
        }
        return awsDynamoDbClientSingleton;
    }
}
