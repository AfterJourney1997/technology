package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleRights {

    private Integer rrId;
    private Integer rrRoleId;
    private Integer rrRightsId;

}