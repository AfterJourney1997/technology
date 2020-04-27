package com.technologygarden.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedMemo {
    private Integer id;
    private Integer cId;
    private String cName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private Integer result;
    private String comment;
    private String approver;//审批人
}