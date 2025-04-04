package com.api.model;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDetails {
    private String name;
    private int processId;
    private String memory;
    private String peakMemory;
    private int thread;
    private String path;
}
