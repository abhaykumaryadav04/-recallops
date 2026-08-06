package com.a4b.recallops.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a4b.recallops.dto.AgentMemoryResponse;
import com.a4b.recallops.model.AgentMemory;

import com.a4b.recallops.repo.AgentMemoryRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AgentMemoryService {
    @Autowired
    private AgentMemoryRepo agentMemoryRepo;
    

    public AgentMemoryResponse getIncidentMemoryById(Long id) throws Exception{
        AgentMemory incident=agentMemoryRepo.findByIncidentId(id).orElseThrow(()-> new Exception("Memory does not exist"));
       AgentMemoryResponse response=AgentMemoryResponse.builder().createdAt(incident.getCreatedAt())
                                                                 .id(incident.getId())
                                                                 .incidentId(incident.getIncident().getId())
                                                                 .problemSummary(incident.getProblemSummary())
                                                                 .rootCause(incident.getRootCause())
                                                                 .solution(incident.getSolution())
                                                                 .build();
        return response;
    }
    public List<AgentMemoryResponse> getAllMemory(){
        List<AgentMemory> memories=agentMemoryRepo.findAll();
        List<AgentMemoryResponse> response=new ArrayList<>();
        for(AgentMemory memory:memories){
            AgentMemoryResponse r=AgentMemoryResponse.builder().createdAt(memory.getCreatedAt())
                                                     .id(memory.getId())
                                                     .incidentId(memory.getIncident().getId())
                                                     .problemSummary(memory.getProblemSummary())
                                                     .rootCause(memory.getRootCause())
                                                     .solution(memory.getSolution())
                                                     .build();
        response.add(r);
        }
        return response;


    }
    public List<AgentMemoryResponse> getRelevantMemory(String query){
         List<AgentMemory> memories=agentMemoryRepo.findByProblemSummaryIgnoreCase(query);
        List<AgentMemoryResponse> response=new ArrayList<>();
        for(AgentMemory memory:memories){
            AgentMemoryResponse r=AgentMemoryResponse.builder().createdAt(memory.getCreatedAt())
                                                     .id(memory.getId())
                                                     .incidentId(memory.getIncident().getId())
                                                     .problemSummary(memory.getProblemSummary())
                                                     .rootCause(memory.getRootCause())
                                                     .solution(memory.getSolution())
                                                     .build();
        response.add(r);
        }
        return response;
    }

}
