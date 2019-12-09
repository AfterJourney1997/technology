package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeviceProperty {

    private Integer devicePropertyId;
    private Integer deviceId;
    private Integer categoryId;
    private Integer propertyId;
    private String propertyValue;

}
