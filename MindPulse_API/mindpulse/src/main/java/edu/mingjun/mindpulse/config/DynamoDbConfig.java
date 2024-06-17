package edu.mingjun.mindpulse.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityRequest;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityResponse;

import java.util.Scanner;


@Getter
@Setter
@Configuration
@NoArgsConstructor
public class DynamoDbConfig implements CommandLineRunner {

    public void connectToAws(String accessKeyId, String secretAccessKey, String sessionToken) {
        DynamoDbCredentials dynamoDbCredentials = DynamoDbCredentials.getInstance();
        DynamoDbClientManager dynamoDbClientManager = DynamoDbClientManager.getInstance();

        dynamoDbCredentials.setAccessKeyId(accessKeyId);
        dynamoDbCredentials.setSecretAccessKey(secretAccessKey);
        dynamoDbCredentials.setSessionToken(sessionToken);

        StsClient stsClient = StsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(dynamoDbCredentials.getAwsCredentials()))
                .region(Region.of(DynamoDbRegion.AWS_SSO_REGION))
                .build();

        GetCallerIdentityRequest getCallerIdentityRequest = GetCallerIdentityRequest.builder().build();
        GetCallerIdentityResponse response = stsClient.getCallerIdentity(getCallerIdentityRequest);

        dynamoDbClientManager.setDynamoDbClient(DynamoDbClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(dynamoDbCredentials.getAwsCredentials()))
                .region(Region.of(DynamoDbRegion.AWS_DYNAMODB_REGION))
                .build()
        );

        dynamoDbClientManager.setDynamoDbEnhancedClient(DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClientManager.getDynamoDbClient())
                .build()
        );

        System.out.println("Account: " + response.account());
        System.out.println("UserId: " + response.userId());
        System.out.println("Arn: " + response.arn());
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter AWS Access Key ID: ");
        String accessKeyId = scanner.nextLine();

        System.out.print("Enter AWS Secret Access Key: ");
        String secretAccessKey = scanner.nextLine();

        System.out.print("Enter AWS Session Token: ");
        String sessionToken = scanner.nextLine();
        this.connectToAws(accessKeyId, secretAccessKey, sessionToken);
    }
}
