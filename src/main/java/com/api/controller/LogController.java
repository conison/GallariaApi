package com.api.controller;

import com.api.model.ApiLog;
import com.api.model.PXELog;
import com.api.model.WorkflowLog;
import com.api.service.LogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/pxe/{hostname}")
    public PXELog getPXELog(@PathVariable String hostname) {
        return logService.fetchPXELog(hostname);
    }

    @GetMapping("/{processInstanceId}/{traceId}")
    public ApiLog getApiLog(@PathVariable String processInstanceId, @PathVariable String traceId) {
        return logService.fetchApiLog(traceId);
    }
}

