package com.wellness.backend.dto.request;

import com.wellness.backend.enums.HealthStatus;
import lombok.Data;

@Data
public class ReactivatePatientRequest {
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String mainCondition;
    private String secondaryConditions;
    private HealthStatus healthStatus;
    private String justification;
}
