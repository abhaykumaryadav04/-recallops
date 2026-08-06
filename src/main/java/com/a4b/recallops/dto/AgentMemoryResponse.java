package com.a4b.recallops.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentMemoryResponse{
 private Long id;
 private Long incidentId;
 private String problemSummary;
 private String rootCause; 
 private String solution;
 private LocalDateTime createdAt; 

}
