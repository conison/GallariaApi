package com.api.model;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NetworkDetails {
    private String dnsSuffix;
    private String fqdn;
    private String name;
    private String type;
    private String ip;
    private String mac;
    private String gateway;
    private String subnet;
}
