package edu.mingjun.mindpulse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class TherapistAvailability {
    private String pk;
    private String sk;
    private String id;
    List<LocalDate> offDates;
    List<Integer> workingDays;
    LocalTime workingStartTime;
    LocalTime workingEndTime;
    List<LocalDateTime> unavailableSlots;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getPk() {
        return this.pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
        this.id = extractIdFromSortKey(pk);
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getSk() {
        return this.sk;
    }

    @DynamoDbIgnore
    public String getId() {
        return this.id;
    }

    private String extractIdFromSortKey(String pk) {
        if (pk != null && pk.startsWith("therapist#")) {
            return pk.substring(10);
        }
        return null;
    }
}
