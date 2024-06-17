package edu.mingjun.mindpulse.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DynamoDbCredentials {
    private static DynamoDbCredentials dynamoDbCredentialsSingleton = null;

    private String accessKeyId;
    private String secretAccessKey;
    private String sessionToken;

    public static DynamoDbCredentials getInstance() {
        if (dynamoDbCredentialsSingleton == null) {
            dynamoDbCredentialsSingleton = new DynamoDbCredentials();
        }
        return dynamoDbCredentialsSingleton;
    }

    public AwsSessionCredentials getAwsCredentials() {
        if (!this.accessKeyId.isEmpty() && !this.secretAccessKey.isEmpty() && !this.sessionToken.isEmpty()) {
            return AwsSessionCredentials.create(this.accessKeyId, this.secretAccessKey, this.sessionToken);
        } else{
            return null;
        }
    }
}
