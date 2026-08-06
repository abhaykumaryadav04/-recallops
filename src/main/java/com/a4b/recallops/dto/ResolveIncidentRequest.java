package com.a4b.recallops.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResolveIncidentRequest {
    private String problemSummary;
    private String solution;
    private String rootCause;

}
