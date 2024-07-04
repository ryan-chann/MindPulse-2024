package edu.mingjun.mindpulse.repository;

import edu.mingjun.mindpulse.model.TaxRate;
import edu.mingjun.mindpulse.singleton.AwsDynamoDbTableSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Slf4j
@Repository
public class TaxRateRepository {
    private final AwsDynamoDbTableSingleton awsDynamoDbTableSingleton;

    public TaxRateRepository() {
        awsDynamoDbTableSingleton = AwsDynamoDbTableSingleton.getInstance();
    }

    public TaxRate findTaxRate() {
        try{
            QueryEnhancedRequest queryEnhancedRequest = QueryEnhancedRequest.builder()
                    .queryConditional(QueryConditional.keyEqualTo(pk -> pk.partitionValue("tax")))
                    .build();

            return awsDynamoDbTableSingleton.getTaxRateTable().query(queryEnhancedRequest).items().stream().findFirst().orElse(null);
        } catch (DynamoDbException e){
            log.error(STR."Failed to execute findTaxRate, error message \{e.getMessage()}");
            throw new RuntimeException("Failed to query", e);
        }
    }
}
