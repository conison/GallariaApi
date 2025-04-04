package com.api.service;

import com.api.model.ServiceDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ServiceService {
    public List<ServiceDetails> getAllServices() {
        return Arrays.asList(
                new ServiceDetails("MECM Agent", "Running", "Automatic", 1234, "SYSTEM", "/usr/bin/mecm"),
                new ServiceDetails("Database Service", "Stopped", "Manual", null, "DBUser", "/usr/bin/dbservice")
        );
    }
}
