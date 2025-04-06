package com.api.service;

import com.api.model.ApiLog;
import com.api.model.PXELog;
import com.api.model.WorkflowLog;
import com.api.model.WorkflowNode;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LogService {

    public PXELog fetchPXELog(String hostname) {
        return new PXELog(hostname, Arrays.asList(
                "[00:00:01] Initializing network...",
                "[00:00:05] DHCP request sent...",
                "[00:00:08] DHCP lease acquired: 192.168.1.100",
                "[00:00:12] PXE boot file downloaded: pxelinux.0",
                "[00:00:15] Booting OS installer...",
                "[00:00:20] Kernel loaded successfully.",
                "[00:00:25] Installation started..."
        ));
    }

    public WorkflowLog fetchWorkflowLog(String hostname) {
        List<WorkflowNode> nodes = Arrays.asList(
                new WorkflowNode("Process Started", "admin", "2025-04-04T10:00:00Z", "12345", null, "START", "Workflow initiated by admin.", "/api/logs/12345/start"),
                new WorkflowNode("User Approval", "johndoe", "2025-04-04T10:05:00Z", "12345", "Process Started", null, "User johndoe approved the process.", "/api/logs/12345/approval"),
                new WorkflowNode("Service Execution", "system", "2025-04-04T10:10:00Z", "12345", "User Approval", null, "System executed the necessary services.", "/api/logs/12345/service"),
                new WorkflowNode("Process Completed", "admin", "2025-04-04T10:15:00Z", "12345", "Service Execution", "END", "Workflow successfully completed.", "/api/logs/12345/completion")
        );
        return new WorkflowLog(nodes);
    }

    public ApiLog fetchApiLog(String traceId) {
        return new ApiLog(
                "API Log for " + traceId,
                Arrays.asList(
                        "[INFO] Request received at /api/logs/" + traceId,
                        "[DEBUG] Processing request...",
                        "[INFO] Response sent successfully."
                )
        );
    }
}

