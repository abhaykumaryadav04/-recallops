package com.a4b.recallops.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a4b.recallops.model.AgentMemory;


public interface AgentMemoryRepo extends JpaRepository<AgentMemory,Long> {
  Optional<AgentMemory> findByIncidentId(Long id);  
 

    
}