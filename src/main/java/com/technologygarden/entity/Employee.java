package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private Integer eId;

    private String eName;

    private String eSex;

    private Integer zId;

    private String ePosition;

    private Integer xId;

    private String fileName;

    private Integer cId;

    private String idCard;

    private String phone;

    private String zName;//用来显示政治面貌，前端便于显示

    private String xName;//用来显示学历，前端便于显示

    private Integer infoid;//用来接收前端对象的infoid

    private List<String> filePathList;//用来返回给前端文件的路径

    private List<String> fileNameList;//用来返回给前端文件的名字

}