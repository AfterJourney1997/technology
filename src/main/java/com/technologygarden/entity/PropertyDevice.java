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
public class PropertyDevice {

    private Integer propertyDeviceId;
    private Integer kind;
    private Integer categoryId;
    private String categoryName;
    private String property;
    private String remark;
    private DeviceProperty deviceProperty = null;
    private List<Device> deviceList = null;

}