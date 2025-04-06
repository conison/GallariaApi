package com.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowNode {
    private String taskName;
    private String user;
    private String timestamp;
    private String processInstanceId;
    private String parentTask;
    private String type;
    private String logDetails;
    private String apiLogUrl;
}
