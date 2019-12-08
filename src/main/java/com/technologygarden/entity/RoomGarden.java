package com.technologygarden.entity;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomGarden {

    private Integer id;
    private String room;
    private Float area;
    private Integer status;
    private Integer companyId;
    private String companyName;
    private Integer buildingId;
    private Building building = null;

}