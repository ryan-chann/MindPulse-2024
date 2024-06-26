package edu.mingjun.mindpulse.repository;

import edu.mingjun.mindpulse.config.AwsDynamoDbTable;
import edu.mingjun.mindpulse.model.TherapistInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class TherapistInfoRepository {
    private final AwsDynamoDbTable awsDynamoDbTable;

    public TherapistInfoRepository(){
        this.awsDynamoDbTable = AwsDynamoDbTable.getInstance();
    }

    public List<TherapistInfo> findAll() {
        try{
            Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
            expressionAttributeValues.put(":partitionPrefix", AttributeValue.builder().s("therapist").build());
            expressionAttributeValues.put(":sortPrefix", AttributeValue.builder().s("info").build());

            Expression filterExpression = Expression.builder()
                    .expression("begins_with(PK, :partitionPrefix) and begins_with(SK, :sortPrefix)")
                    .expressionValues(expressionAttributeValues)
                    .build();

            ScanEnhancedRequest scanEnhancedRequest = ScanEnhancedRequest.builder()
                    .filterExpression(filterExpression)
                    .build();

            return awsDynamoDbTable.getTherapistInfoTable().scan(scanEnhancedRequest).items().stream().collect(Collectors.toList());
        } catch (DynamoDbException e) {
            log.error(STR."Failed to execute findAll, error message \{e.getMessage()}");
            throw new RuntimeException("Failed to query", e);
        }
    }
}
