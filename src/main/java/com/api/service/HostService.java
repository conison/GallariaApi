package com.api.service;

import com.api.model.Host;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HostService {
    public List<Host> getAllHosts() {
        return Arrays.asList(
                new Host("NDC123467", "00:11:22:33:44:55", "Singapore", "user1", "2025-03-25"),
                new Host("NDC908540", "00:11:22:33:44:59", "Hong Kong", "user2", "2025-03-24")
        );
    }

    public Host getHostDetails(String hostname) {
        return getAllHosts().stream()
                .filter(host -> host.getHostname().equals(hostname))
                .findFirst()
                .orElse(null);
    }
}
