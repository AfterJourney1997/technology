package com.technologygarden.entity;

import lombok.*;

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

   }