package com.technologygarden.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomCompany {

    private Integer id;
    private String room;
    private Float area;
    private Integer status;
    private Integer companyId;
    private String companyName;
    private String owner;
    private Float owningTime;
    private Integer buildingId;
    private Building building = null;

}