package com.technologygarden.entity;

import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Integer id;
    private String account;
    private String password;
    private Integer role;
    private Integer infoid;
    private List<Rights> rightsList = null;

   }