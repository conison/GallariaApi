package com.api.model;

public class WorkflowStatusUpdate {
    private String processInstanceId;
    private String taskId;
    private String status;

    public WorkflowStatusUpdate(String processInstanceId, String taskId, String status) {
        this.processInstanceId = processInstanceId;
        this.taskId = taskId;
        this.status = status;
    }

    // Getters and setters
}
