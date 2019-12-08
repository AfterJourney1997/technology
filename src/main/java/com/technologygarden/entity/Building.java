package com.technologygarden.entity;

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
    private Date createtime;
    private Float money;

}