package com.a4b.recallops.model;

import java.time.LocalDateTime;

import com.a4b.recallops.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String description;
    @Column(nullable = false)
    private String title;
    @Column(length = 1500)
    private String logs;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime resolveAt;

@PrePersist
private void onCreated(){
    createdAt=LocalDateTime.now();
}
  @PrePersist
    public void onCreate() {
        status=Status.OPEN;
    }
}
