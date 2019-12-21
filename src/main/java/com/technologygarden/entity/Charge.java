package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Charge {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    private Integer cid;

    private Integer roomid;

    private String costSort;

    private Double charge;

    private int status;

    private String notes;

    private String cName;//存放cid对应的企业名

    private String roomName;//存放roomid对应的房间名

}