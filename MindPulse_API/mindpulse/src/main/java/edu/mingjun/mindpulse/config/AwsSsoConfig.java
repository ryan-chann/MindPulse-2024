package edu.mingjun.mindpulse.config;

import edu.mingjun.mindpulse.global.GlobalVariables;
import edu.mingjun.mindpulse.singleton.AwsDynamoDbClientSingleton;
import edu.mingjun.mindpulse.singleton.AwsDynamoDbTableSingleton;
import edu.mingjun.mindpulse.singleton.AwsSsoCredentialsSingleton;
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
        AwsSsoCredentialsSingleton awsSsoCredentialsSingleton = AwsSsoCredentialsSingleton.getInstance();
        AwsDynamoDbClientSingleton awsDynamoDbClientSingleton = AwsDynamoDbClientSingleton.getInstance();

        awsSsoCredentialsSingleton.setAccessKeyId(accessKeyId);
        awsSsoCredentialsSingleton.setSecretAccessKey(secretAccessKey);
        awsSsoCredentialsSingleton.setSessionToken(sessionToken);

        StsClient stsClient = StsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsSsoCredentialsSingleton.getAwsCredentials()))
                .region(Region.of(GlobalVariables.AWS_SSO_REGION))
                .build();

        GetCallerIdentityRequest getCallerIdentityRequest = GetCallerIdentityRequest.builder().build();
        try {
            GetCallerIdentityResponse response = stsClient.getCallerIdentity(getCallerIdentityRequest);

            awsDynamoDbClientSingleton.setDynamoDbClient(DynamoDbClient.builder()
                    .credentialsProvider(StaticCredentialsProvider.create(awsSsoCredentialsSingleton.getAwsCredentials()))
                    .region(Region.of(GlobalVariables.AWS_DYNAMODB_REGION))
                    .build()
            );

            awsDynamoDbClientSingleton.setDynamoDbEnhancedClient(DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(awsDynamoDbClientSingleton.getDynamoDbClient())
                    .build()
            );

            AwsDynamoDbTableSingleton awsDynamoDbTableSingleton = AwsDynamoDbTableSingleton.getInstance();

            if (awsDynamoDbClientSingleton.getDynamoDbClient() != null){
                log.info("DynamoDB client created");
                if (awsDynamoDbClientSingleton.getDynamoDbEnhancedClient() != null) {
                    log.info("DynamoDB enhanced client created");
                }
            }

            System.out.println("--------------------------------------");
            System.out.println("CONNECTION SUCCESSFUL");
            System.out.println("--------------------------------------");
            System.out.println(STR."Account: \{response.account()}");
            System.out.println(STR."User ID: \{response.userId()}");
            System.out.println(STR."Amazon Resource Name: \{response.arn()}");
        } catch (StsException _) {
            log.error("Invalid AWS credentials, Exiting program...");
            System.exit(1);
        }
    }

    public static void main() throws IOException {
//        Scanner scanner = new Scanner(System.in);

        String accessKeyId = System.getenv("AWS_ACCESS_KEY_ID");
        String secretAccessKey = System.getenv("AWS_SECRET_ACCESS_KEY");
        String sessionToken = System.getenv("AWS_SESSION_TOKEN");

//        System.out.print("Enter AWS Access Key ID: ");
//        String accessKeyId = scanner.nextLine();
//
//        System.out.print("Enter AWS Secret Access Key: ");
//        String secretAccessKey = scanner.nextLine();
//
//        System.out.print("Enter AWS Session Token: ");
//        String sessionToken = scanner.nextLine();
        connectToAws(accessKeyId, secretAccessKey, sessionToken);
    }
}
