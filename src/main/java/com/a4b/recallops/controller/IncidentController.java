package com.a4b.recallops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.a4b.recallops.dto.IncidentRequest;
import com.a4b.recallops.dto.IncidentResponse;
import com.a4b.recallops.enums.Status;
import com.a4b.recallops.model.Incident;
import com.a4b.recallops.service.IncidentService;



@RestController
@RequestMapping("/api")
public class IncidentController {
    @Autowired
    private IncidentService incidentService;
    @PostMapping("/incidents/create")
    public ResponseEntity<String> createIncident(@RequestBody IncidentRequest request){
        return ResponseEntity.ok(incidentService.createIncident(request));
    }
    @GetMapping("/incidents/{id}")
    public ResponseEntity<IncidentResponse> getIncidentByid(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(incidentService.getIncidentByid(id));
    }
    @GetMapping("/incidents/status/{status}")
    public ResponseEntity<List<Incident>> getIncidentByStatus(@PathVariable Status status){
        return ResponseEntity.ok(incidentService.getIncidentByStatus(status));
    }



}
