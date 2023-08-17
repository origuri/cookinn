package com.example.cookinn.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDto {
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
