package com.api.controller;

import com.api.model.*;
import com.api.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/preregistration")
public class PreregistrationController {
    private final PreregistrationService preregistrationService;

    public PreregistrationController(PreregistrationService preregistrationService) {
        this.preregistrationService = preregistrationService;
    }

    @GetMapping("/unregistered")
    public List<Device> getUnregisteredDevices() {
        return preregistrationService.getUnregisteredDevices();
    }

    @GetMapping("/registered")
    public List<Device> getRegisteredDevices() {
        return preregistrationService.getRegisteredDevices();
    }
}
