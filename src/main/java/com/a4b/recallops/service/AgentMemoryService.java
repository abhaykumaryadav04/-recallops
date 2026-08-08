package com.a4b.recallops.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a4b.recallops.dto.AgentMemoryResponse;
import com.a4b.recallops.model.AgentMemory;

import com.a4b.recallops.repo.AgentMemoryRepo;
import com.a4b.recallops.vector.VectorMemoryService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AgentMemoryService {
    @Autowired
    private AgentMemoryRepo agentMemoryRepo;
    @Autowired
    private VectorMemoryService vectorMemoryService;
    

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
         List<AgentMemory> memories=agentMemoryRepo.findByProblemSummaryContainsIgnoreCase(query);
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
    public List<AgentMemoryResponse> getReleventMemories(String query) throws Exception{
     List<Document> documents=vectorMemoryService.similaritySearch(query);
     List<AgentMemoryResponse> responses=new ArrayList<>();
     for(Document document : documents){
        Object memoryIdObject = document.getMetadata().get("MemoryId");
        Long memoryId = Long.valueOf(memoryIdObject.toString());
     AgentMemory memory=agentMemoryRepo.findById(memoryId).orElseThrow(()-> new Exception("No data found"));
     AgentMemoryResponse response=AgentMemoryResponse.builder().createdAt(memory.getCreatedAt())
                                                                .id(memory.getId())
                                                                .incidentId(memory.getIncident().getId())
                                                                .problemSummary(memory.getProblemSummary())
                                                                .rootCause(memory.getRootCause())
                                                                .solution(memory.getSolution())
                                                                .build();
        responses.add(response);
     }
     return responses;
    }

}
