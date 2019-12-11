package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseInformation {

    private Integer cId;
    private String cName;
    private String cCategory;
    private Integer cLegalperson;
    private String cCode;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date cDate;
    private String taxpayerType;
    private Double cFund;
    private String cProduct;
    private String fileName;
    private Integer cEmployee;
    private String cInformation;
    private int cStatus;

    private List<String> filePathList;//存放所有的文件路径
    private List<String> filePathName;//存放所有的文件名字
    private Integer infoid;//当前登录对象的infoid
    private LegalPerson legalPerson = null;//存放法人信息

    private String account;//存放企业对应账号，企业审批使用

}