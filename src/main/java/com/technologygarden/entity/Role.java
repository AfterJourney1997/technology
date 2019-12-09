package com.technologygarden.entity;

import lombok.*;

@Data
@Builder
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