package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer eId;

    private String eName;

    private String eSex;

    private Integer zId;

    private String ePosition;

    private Integer xId;

    private String fileName;

    private MultipartFile blFile;

    private Integer cId;

    private String zName;

    private String xName;

    private Role role=null;

    private String filePath;


}