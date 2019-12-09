package com.technologygarden.entity;

import lombok.*;

import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseInformation {
    private Integer eId;

    private String eName;

    private String eCategory;

    private String eCode;

    private Date eDate;

    private String taxpayerType;

    private Double eFund;

    private String eProduct;

    private String fileName;

    private Role role =null;

    }