package edu.mingjun.mindpulse.repository;

import edu.mingjun.mindpulse.singleton.AwsDynamoDbTableSingleton;
import edu.mingjun.mindpulse.model.TherapistEducation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Slf4j
@Repository
public class TherapistEducationRepository {
    private final AwsDynamoDbTableSingleton awsDynamoDbTableSingleton;

    public TherapistEducationRepository(){
        this.awsDynamoDbTableSingleton = AwsDynamoDbTableSingleton.getInstance();
    }

    public TherapistEducation findById(String id){
        try{
            QueryEnhancedRequest queryEnhancedRequest = QueryEnhancedRequest.builder()
                    .queryConditional(QueryConditional.keyEqualTo(pk -> pk.partitionValue(STR."therapist#\{id}").sortValue("education")))
                    .build();

            return awsDynamoDbTableSingleton.getTherapistEducationTable().query(queryEnhancedRequest).items().stream().findFirst().orElse(null);
        } catch (DynamoDbException e){
            log.error(STR."Failed to execute findById, error message \{e.getMessage()}");
            throw new RuntimeException("Failed to query", e);
        }
    }
}
