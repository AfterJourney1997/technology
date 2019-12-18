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
public class Opinion {
    private Integer id;

    private String opinion;

    private int status;

    private Integer cId;

    private Integer roomId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date datetime;

    private Integer infoid;//获取当前登录对象的infoid

    private String cName;//存放cId对应的企业名

    private String roomName;//存放roomId对应的房间名

}