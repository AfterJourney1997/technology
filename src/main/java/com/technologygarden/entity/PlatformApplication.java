package com.technologygarden.entity;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlatformApplication {
    private Integer pId;

    private String pName;

    private String pCategory;

    private String pGrade;

    private Integer cId;

    private Integer status;

    private String cName;

    private Role role =null;

}