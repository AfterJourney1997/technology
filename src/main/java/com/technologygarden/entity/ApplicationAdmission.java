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
public class ApplicationAdmission {
    private Integer id;

    private String cName;

    private String cCategory;

    private String cLegalperson;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date cDate;

    private Double cFund;

    private String cProduct;

    private Integer cEmployee;

    private String cInformation;

    private int cStatus;

    private MultipartFile blFile;

    private String fileName;
    private Role role=null;

}