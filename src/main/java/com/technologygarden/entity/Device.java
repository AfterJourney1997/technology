package com.technologygarden.entity;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    private Integer deviceId;
    private Integer categoryId;
    private String deviceName;
    private Integer total;
    private Integer remain;
    private Float piece;
    private Integer owner;
    private Integer kind;
    private List<PropertyDevice> propertyDeviceList = null;

}