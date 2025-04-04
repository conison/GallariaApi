package com.api.service;

import com.api.model.ProcessDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProcessService {
    public List<ProcessDetails> getProcesses() {
        return Arrays.asList(
                new ProcessDetails("chrome.exe", 4321, "200MB", "500MB", 12, "C:\\Program Files\\Google\\Chrome.exe"),
                new ProcessDetails("node.exe", 1234, "150MB", "300MB", 8, "C:\\Program Files\\NodeJS\\node.exe")
        );
    }
}
