package com.a4b.recallops.dto;

import java.time.LocalDateTime;

import com.a4b.recallops.enums.Status;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncidentResponse {
    private String title;
    private String desc;
    private String logs;
    private LocalDateTime createdAt;
    private Status status;

}
