package com.a4b.recallops.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class AgentMemory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String rootCause;
   private String problem;
   private String solution;
   private String problemSummary;
   @OneToOne
   @JoinColumn(name = "incident_id")
   private Incident incident;
   private LocalDateTime createdAt;
   @PrePersist
   private void createdAt(){
    createdAt=LocalDateTime.now();
   }


}
