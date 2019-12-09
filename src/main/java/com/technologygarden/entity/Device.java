package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    private Integer deviceId;
    private Integer categoryId;
    private String deviceName;
    private Integer total;
    private Integer remain;
    private Integer owner;
    private Integer kind;
    private List<PropertyDevice> propertyDeviceList = null;

}