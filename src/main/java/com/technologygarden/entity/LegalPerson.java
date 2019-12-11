package com.technologygarden.entity;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LegalPerson {

    private Integer lpId;
    private String lpName;
    private Integer lpSex;
    private Integer lpDegreeId;
    private Integer lpJtId;
    private String lpCName;
    private Degree degree = null;
    private JobTitle jobTitle = null;

}