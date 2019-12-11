package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cooperation {
    private Integer id;

    private String name;

    private String partner;

    private Double money;

    private String major;

    private Integer people;

    private String category;

    private Integer day;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date date;

    private Integer cId;

    private Integer infoid;

}