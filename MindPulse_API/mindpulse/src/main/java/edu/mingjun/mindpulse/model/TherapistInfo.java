package edu.mingjun.mindpulse.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class TherapistInfo {
    private String pk;
    private String sk;
    private String id;
    private String name;
    private int type;
    private boolean isAvailable;
    private String imageUrl;

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

    @DynamoDbAttribute("isAvailable")
    public boolean isAvailable() {
        return this.isAvailable;
    }

    private String extractIdFromSortKey(String pk) {
        if (pk != null && pk.startsWith("therapist#")) {
            return pk.substring(10);
        }
        return null;
    }
}
