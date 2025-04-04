package com.api.model;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Host {
    private String hostname;
    private String mac;
    private String location;
    private String registeredBy;
    private String regDate;
}
