package com.technologygarden.entity;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Awards {
    private Integer id;
    private String awardsName;

}