package com.a4b.recallops.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a4b.recallops.enums.Status;
import com.a4b.recallops.model.Incident;

public interface IncidentRepo extends JpaRepository<Incident,Long> {
List<Incident> findByStatusOrderByCreatedAtDesc(Status status);
}
