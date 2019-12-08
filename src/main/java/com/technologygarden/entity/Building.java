package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    
    private Integer id;
    private String name;
    private Float area;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date createtime;
    private Float money;

}