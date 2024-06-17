package edu.mingjun.mindpulse.config;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DynamoDbClientManager {
    private static DynamoDbClientManager dynamoDbClientManagerSingleton = null;

    private DynamoDbClient dynamoDbClient;
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public static DynamoDbClientManager getInstance() {
        if (dynamoDbClientManagerSingleton == null) {
            dynamoDbClientManagerSingleton = new DynamoDbClientManager();
        }
        return dynamoDbClientManagerSingleton;
    }
}
