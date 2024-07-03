package edu.mingjun.mindpulse.repository;

import edu.mingjun.mindpulse.model.ServiceOffered;
import edu.mingjun.mindpulse.singleton.AwsDynamoDbTableSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class OfferedServiceRepository {
    private final AwsDynamoDbTableSingleton awsDynamoDbTableSingleton;

    public OfferedServiceRepository(){
        this.awsDynamoDbTableSingleton = AwsDynamoDbTableSingleton.getInstance();
    }

    public ServiceOffered findById(String id) {
        try{
            QueryEnhancedRequest queryEnhancedRequest = QueryEnhancedRequest.builder()
                    .queryConditional(QueryConditional.keyEqualTo(pk -> pk.partitionValue("serviceOffered").sortValue(STR."\{id}")))
                    .build();

            return awsDynamoDbTableSingleton.getServiceOfferedTable().query(queryEnhancedRequest).items().stream().findFirst().orElse(null);
        } catch (DynamoDbException e) {
            log.error(STR."Failed to execute findById, error message \{e.getMessage()}");
            throw new RuntimeException("Failed to query", e);
        }
    }

    public List<ServiceOffered> findAll() {
        try{
            QueryEnhancedRequest queryEnhancedRequest = QueryEnhancedRequest.builder()
                    .queryConditional(QueryConditional.keyEqualTo(pk -> pk.partitionValue("serviceOffered")))
                    .build();

            return awsDynamoDbTableSingleton.getServiceOfferedTable().query(queryEnhancedRequest).items().stream().collect(Collectors.toList());
        } catch (DynamoDbException e) {
            log.error(STR."Failed to execute findAll, error message \{e.getMessage()}");
            throw new RuntimeException("Failed to query", e);
        }
    }
}
