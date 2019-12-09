package com.technologygarden.entity;

import lombok.*;

import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationAdmission {
    private Integer id;

    private String cName;

    private String cCategory;

    private String cLegalperson;

    private Date cDate;

    private Double cFund;

    private String cProduct;

    private Integer cEmployee;

    private String cInformation;

    private Integer cStatus;

    private String fileName;
    private Role role=null;

}