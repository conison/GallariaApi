package com.api.service;

import com.api.model.NetworkDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NetworkService {
    public List<NetworkDetails> getNetworkDetails() {
        return Arrays.asList(
                new NetworkDetails("example.com", "host1.example.com", "eth0", "Ethernet", "192.168.1.10", "AA:BB:CC:DD:EE:FF", "192.168.1.1", "255.255.255.0")
        );
    }
}