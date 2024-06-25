package edu.mingjun.mindpulse.config;

import edu.mingjun.mindpulse.global.AwsDynamoDbRegion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityRequest;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityResponse;
import software.amazon.awssdk.services.sts.model.StsException;

import java.io.IOException;
import java.util.Scanner;


@Slf4j
@Getter
@Setter
@Configuration
@NoArgsConstructor
public class AwsSsoConfig {

    private static void connectToAws(String accessKeyId, String secretAccessKey, String sessionToken) {
        AwsSsoCredentials awsSsoCredentials = AwsSsoCredentials.getInstance();
        AwsDynamoDbClient awsDynamoDbClient = AwsDynamoDbClient.getInstance();

        awsSsoCredentials.setAccessKeyId(accessKeyId);
        awsSsoCredentials.setSecretAccessKey(secretAccessKey);
        awsSsoCredentials.setSessionToken(sessionToken);

        StsClient stsClient = StsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsSsoCredentials.getAwsCredentials()))
                .region(Region.of(AwsDynamoDbRegion.AWS_SSO_REGION))
                .build();

        GetCallerIdentityRequest getCallerIdentityRequest = GetCallerIdentityRequest.builder().build();
        try {
            GetCallerIdentityResponse response = stsClient.getCallerIdentity(getCallerIdentityRequest);

            awsDynamoDbClient.setDynamoDbClient(DynamoDbClient.builder()
                    .credentialsProvider(StaticCredentialsProvider.create(awsSsoCredentials.getAwsCredentials()))
                    .region(Region.of(AwsDynamoDbRegion.AWS_DYNAMODB_REGION))
                    .build()
            );

            awsDynamoDbClient.setDynamoDbEnhancedClient(DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(awsDynamoDbClient.getDynamoDbClient())
                    .build()
            );

            if (awsDynamoDbClient.getDynamoDbClient() != null){
                log.info(STR."DynamoDB client created");
                if (awsDynamoDbClient.getDynamoDbEnhancedClient() != null) {
                    log.info(STR."DynamoDB enhanced client created");
                }
            }

            System.out.println("--------------------------------------");
            System.out.println("CONNECTION SUCCESSFUL");
            System.out.println("--------------------------------------");
            System.out.println("Account: " + response.account());
            System.out.println("User ID: " + response.userId());
            System.out.println("Amazon Resource Name: " + response.arn());
        } catch (StsException _) {
            log.error("Invalid AWS credentials, Exiting program...");
            System.exit(1);
        }
    }

    public static void main() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter AWS Access Key ID: ");
        String accessKeyId = scanner.nextLine();

        System.out.print("Enter AWS Secret Access Key: ");
        String secretAccessKey = scanner.nextLine();

        System.out.print("Enter AWS Session Token: ");
        String sessionToken = scanner.nextLine();
        connectToAws(accessKeyId, secretAccessKey, sessionToken);
    }
}
