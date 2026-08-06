package com.a4b.recallops.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a4b.recallops.dto.IncidentRequest;
import com.a4b.recallops.dto.IncidentResponse;
import com.a4b.recallops.dto.ResolveIncidentRequest;
import com.a4b.recallops.enums.Status;
import com.a4b.recallops.model.AgentMemory;
import com.a4b.recallops.model.Incident;
import com.a4b.recallops.repo.AgentMemoryRepo;
import com.a4b.recallops.repo.IncidentRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IncidentService {
    @Autowired
    private IncidentRepo incidentRepo;
    @Autowired
    private AgentMemoryRepo agentMemoryRepo;

    public String  createIncident(IncidentRequest request) {
      Incident incident=Incident.builder().description(request.getDescreption())
                                          .logs(request.getLogs())
                                          .title(request.getTitle())
                                          .build();
      incidentRepo.save(incident);
      return "Incident successfully register";
    }

    public IncidentResponse getIncidentByid(Long id) throws Exception {
        Incident incident=incidentRepo.findById(id).orElseThrow(()-> new Exception("Incident no available"));
        IncidentResponse response=IncidentResponse.builder()
                                                   .createdAt(incident.getCreatedAt())
                                                   .desc(incident.getDescription())
                                                   .logs(incident.getLogs())
                                                   .status(incident.getStatus())
                                                   .title(incident.getTitle())
                                                   .build();
        return response;
    }

    public List<Incident> getIncidentByStatus(Status status) {
       List<Incident> incidents=incidentRepo.findByStatusOrderByCreatedAtDesc(status);
      return incidents;
       
    }

    public String resolvebyId(Long id,ResolveIncidentRequest request) throws Exception {
    Incident incident=incidentRepo.findById(id).orElseThrow(()-> new Exception("Incident couldnot be found"));
    if(!agentMemoryRepo.findByIncidentId(id).isEmpty()){
        throw new Exception("Memory already exist");
    }
    incident.setResolveAt(LocalDateTime.now());
    incident.setStatus(Status.RESOLVED);
    incidentRepo.save(incident);
   
    AgentMemory memory=AgentMemory.builder().incident(incident)
                                  .rootCause(request.getRootCause())
                                  .solution(request.getSolution())
                                  .problemSummary(request.getProblemSummary())
                                  .build();
    agentMemoryRepo.save(memory);
    return "Successfully updated";   
}

}
