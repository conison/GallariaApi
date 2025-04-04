package com.api.service;

import com.api.model.Device;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PreregistrationService {
    public List<Device> getUnregisteredDevices() {
        return Arrays.asList(
                new Device(1, "AA:BB:CC:DD:EE:11","NDC", "User1", "2025-03-25"),
                new Device(2, "AA:BB:CC:DD:EE:22","NDC", "User2", "2025-03-26")
        );
    }

    public List<Device> getRegisteredDevices() {
        return Arrays.asList(
                new Device(3, "AA:BB:CC:DD:EE:33", "NDC", "User3", "2025-03-20")
        );
    }
}