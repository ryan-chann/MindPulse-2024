package edu.mingjun.mindpulse.service;

import edu.mingjun.mindpulse.global.GlobalVariables;
import edu.mingjun.mindpulse.singleton.AwsSsoCredentialsSingleton;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Service
public class FileUploadService {
    private final S3Client s3Client;

    public FileUploadService() {
        this.s3Client = S3Client.builder()
                .region(Region.of(GlobalVariables.AWS_S3_REGION)) // Change to your region
                .credentialsProvider(StaticCredentialsProvider.create(AwsSessionCredentials.create(AwsSsoCredentialsSingleton.getInstance().getAccessKeyId(), AwsSsoCredentialsSingleton.getInstance().getSecretAccessKey(), AwsSsoCredentialsSingleton.getInstance().getSessionToken())))
                .build();
    }

    public boolean uploadFile(byte[] fileBytes, String fileName) {
        String keyName = STR."payments/\{fileName}";

        try{
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(GlobalVariables.AWS_S3_BUCKET_NAME)
                    .key(keyName)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileBytes));
            return true;
        } catch (Exception _){
            log.error("Error upload file");
            return false;
        }
    }
}
