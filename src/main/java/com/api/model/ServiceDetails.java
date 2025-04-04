package com.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDetails {
    private String name;
    private String status;
    private String startupType;
    private Integer processId;
    private String logonAs;
    private String path;
}
