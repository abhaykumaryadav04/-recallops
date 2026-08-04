package com.a4b.recallops.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IncidentRequest {
    private String title;
    private String descreption;
    private String logs;
   
    
}
