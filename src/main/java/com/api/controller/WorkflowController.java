package com.api.controller;

import com.api.model.*;
import com.api.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {
    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @GetMapping
    public List<Workflow> getAllWorkflows() {
        return workflowService.getAllWorkflows();
    }

    @GetMapping("/workflow/{processInstanceId}")
    public ResponseEntity<Map<String, Object>> getWorkflowLogs(@PathVariable String processInstanceId) {
        Map<String, Object> result = workflowService.getWorkflowWithLogs(processInstanceId);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/{id}/pause")
    public ResponseEntity<String> pauseWorkflow(@PathVariable String id) {
        workflowService.pauseWorkflow(id);
        return ResponseEntity.ok("Workflow paused successfully");
    }

    @PostMapping("/{id}/resume")
    public ResponseEntity<String> resumeWorkflow(@PathVariable String id) {
        workflowService.resumeWorkflow(id);
        return ResponseEntity.ok("Workflow resumed successfully");
    }

    @PostMapping("/{id}/retry")
    public ResponseEntity<String> retryWorkflow(@PathVariable String id) {
        workflowService.retryWorkflow(id);
        return ResponseEntity.ok("Workflow retried successfully");
    }

    @PostMapping("/{id}/stop")
    public ResponseEntity<String> stopWorkflow(@PathVariable String id) {
        workflowService.stopWorkflow(id);
        return ResponseEntity.ok("Workflow stopped successfully");
    }

    @PostMapping("/{id}/kill")
    public ResponseEntity<String> killWorkflow(@PathVariable String id) {
        workflowService.killWorkflow(id);
        return ResponseEntity.ok("Workflow killed successfully");
    }
}