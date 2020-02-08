package com.technologygarden.entity;

import lombok.*;

import java.util.List;

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
    private String phone;
    private List<Rights> rightsList = null;
    private EnterpriseInformation enterpriseInformation = null;

   }