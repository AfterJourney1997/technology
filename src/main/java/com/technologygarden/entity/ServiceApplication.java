package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceApplication {
    private Integer id;

    private Integer maintainId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date date;

    private int status;

    private Integer cId;

    private Integer roomId;

    private String roomName;//存放roomId对应的roomName

    private String servicename;//存放maintain对应的servicename服务名，便于显示

    private Integer infoid;//存放当前登录对象的infoid

    private String cName;//存放cId对应的公司名

}