package edu.mingjun.mindpulse.config;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AwsDynamoDbClient {
    private static AwsDynamoDbClient awsDynamoDbClientSingleton = null;

    private DynamoDbClient dynamoDbClient;
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public static AwsDynamoDbClient getInstance() {
        if (awsDynamoDbClientSingleton == null) {
            awsDynamoDbClientSingleton = new AwsDynamoDbClient();
        }
        return awsDynamoDbClientSingleton;
    }
}
