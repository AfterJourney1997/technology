package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    
    private Integer id;
    private String buildingName;
    private Float area;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date createtime;
    private Float money;

}