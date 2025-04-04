package com.api.model;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    private int id;
    private String mac;
    private String type;
    private String onboardedBy;
    private String date;
}
