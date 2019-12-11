package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseInformation {
    private Integer cId;

    private String cName;

    private String cCategory;

    private String cLegalperson;

    private String cCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date cDate;

    private String taxpayerType;

    private Double cFund;

    private String cProduct;

    private String fileName;

    private Integer cEmployee;

    private String cInformation;

    private Integer cStatus;

    private MultipartFile blFile;//用来接受文件

    private String filePath;//用来返回给前端文件路径

    private Integer infoid;//接受当前用户的infoid

}