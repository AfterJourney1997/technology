package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParkEnvironment {
    private Integer id;

    private Integer buildingid;

    private String principal;

    private String phone;

    private String buildingName;//房区名称
}