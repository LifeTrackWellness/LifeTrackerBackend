package com.wellness.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HabitTaskRequest {
    @NotBlank(message = "El nombre de la tarea es obligatorio")
    private String name;
    private String description;
}