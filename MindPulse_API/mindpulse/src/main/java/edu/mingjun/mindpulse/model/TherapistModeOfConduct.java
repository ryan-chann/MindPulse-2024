package edu.mingjun.mindpulse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class TherapistModeOfConduct {
    private String pk;
    private String sk;
    private String id;
    private boolean isInPerson;
    private boolean isOnline;

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

    @DynamoDbAttribute("isInPerson")
    public boolean isInPerson() {
        return this.isInPerson;
    }

    @DynamoDbAttribute("isOnline")
    public boolean isOnline() {
        return this.isOnline;
    }

    private String extractIdFromSortKey(String pk) {
        if (pk != null && pk.startsWith("therapist#")) {
            return pk.substring(10);
        }
        return null;
    }
}
