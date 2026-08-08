package com.a4b.recallops.vector;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

import org.springframework.stereotype.Service;


import com.a4b.recallops.model.AgentMemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VectorMemoryService {
 
private final VectorStore vectorStore;

public VectorMemoryService(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
}

 public void storeMemory(AgentMemory memory){
    String content="problemSummery:"+memory.getProblemSummary()+"\n RootCause:"+memory.getRootCause()+"\n Solution:"+memory.getSolution();
    Map<String,Object> metaData=new HashMap<>();
    metaData.put("MemoryId", memory.getId());
    metaData.put("IncidentId", memory.getIncident().getId());
    Document document=new Document(content,metaData );
    vectorStore.add(List.of(document));
 }
 public List<Document> similaritySearch(String query){
    SearchRequest request=SearchRequest.builder().query(query).topK(5).similarityThreshold(0.77).build();
    return vectorStore.similaritySearch(request);
 }

}
