package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private Integer rId;
    private String rName;
    private Float rArea;
    private Integer rStatus;
    private Integer rCompanyId;
    private String rCompanyName;
    private String rOwner;
    private Integer rOwningTime;
    private Integer rBuildingId;
    private Integer rRoomKind;
    private Building building = null;


}