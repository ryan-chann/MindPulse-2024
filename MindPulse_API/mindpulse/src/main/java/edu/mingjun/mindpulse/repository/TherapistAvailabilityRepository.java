package edu.mingjun.mindpulse.repository;

import edu.mingjun.mindpulse.model.TherapistAssistance;
import edu.mingjun.mindpulse.model.TherapistAvailability;
import edu.mingjun.mindpulse.singleton.AwsDynamoDbTableSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Repository
public class TherapistAvailabilityRepository {
    private final AwsDynamoDbTableSingleton awsDynamoDbTableSingleton;

    public TherapistAvailabilityRepository() {
        awsDynamoDbTableSingleton = AwsDynamoDbTableSingleton.getInstance();
    }

    public TherapistAvailability findById(String id){
        try{
            QueryEnhancedRequest queryEnhancedRequest = QueryEnhancedRequest.builder()
                    .queryConditional(QueryConditional.keyEqualTo(pk -> pk.partitionValue(STR."therapist#\{id}").sortValue("availability")))
                    .build();

            return awsDynamoDbTableSingleton.getTherapistAvailabilityTable().query(queryEnhancedRequest).items().stream().findFirst().orElse(null);
        } catch (DynamoDbException e){
            log.error(STR."Failed to execute findById, error message \{e.getMessage()}");
            throw new RuntimeException("Failed to query", e);
        }
    }

    public void updateOneUnavailableSlotWithTherapistId(String therapistId, LocalDateTime unavailableDateTime){
        try{
            TherapistAvailability therapistAvailability = this.findById(therapistId);

            if (therapistAvailability != null) {
                List<LocalDateTime> unavailableSlots = therapistAvailability.getUnavailableSlots();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                unavailableSlots.add(unavailableDateTime);
                therapistAvailability.setUnavailableSlots(unavailableSlots);

                UpdateItemEnhancedRequest<TherapistAvailability> updateRequest = UpdateItemEnhancedRequest.builder(TherapistAvailability.class)
                        .item(therapistAvailability)
                        .build();

                awsDynamoDbTableSingleton.getTherapistAvailabilityTable().updateItem(updateRequest);
                log.info("Updated unavailable slots for therapistId: {}", therapistId);
            } else {
                log.warn("Therapist with ID {} not found.", therapistId);
            }
        } catch (DynamoDbException e){
            log.error(STR."Failed to execute updateOneUnavailableSlotWithTherapistId, error message \{e.getMessage()}");
            throw new RuntimeException("Failed to query", e);
        }
    }
}
