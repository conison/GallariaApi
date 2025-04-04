package com.api.service;

import com.api.model.Workflow;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WorkflowService {
    public List<Workflow> getAllWorkflows() {
        return Arrays.asList(
                new Workflow(1, "Deploy Server", "Running"),
                new Workflow(2, "Update Config", "Completed")
        );
    }
}