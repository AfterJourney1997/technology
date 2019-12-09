package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDevice {

    private Integer id;
    private Integer kind;
    private Integer categoryId;
    private String name;
    private String property;

}