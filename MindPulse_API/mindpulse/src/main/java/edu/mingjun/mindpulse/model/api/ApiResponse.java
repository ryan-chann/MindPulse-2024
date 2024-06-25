package edu.mingjun.mindpulse.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApiResponse {
    private Boolean isSuccess;
    private String transaction;
    private String message;
    private Object data;
}
