package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rights {

    private Integer rId;
    private String rPerms;
    private String rUrl;
    private String rDescription;


}