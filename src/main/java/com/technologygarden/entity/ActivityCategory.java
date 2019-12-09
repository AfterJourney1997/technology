package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCategory {

    private Integer acId;
    @NotBlank
    private String acCategory;

}