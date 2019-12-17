package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeclareAward {
    private Integer dId;

    private String dName;

    private Integer aId;

    private String filename;

    private int dStatus;

    private Integer cId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date datetime;

    private String dGrade;

    private String dRank;

    private Integer infoid;//用来接收当前用户的infoid

    private String cName;//用来存放cId对应的企业名称

    private String aName;//存放aId对应的名字，奖项名称

    private List<String> filePathList;//用来存放文件路径

    private List<String> fileNameList;//用来存放文件名称


}