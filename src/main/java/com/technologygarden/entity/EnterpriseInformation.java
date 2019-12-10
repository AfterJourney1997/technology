package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date eDate;

    private String taxpayerType;

    private Double eFund;

    private String eProduct;

    private String fileName;

    private MultipartFile blFile;

    private Role role =null;

    }