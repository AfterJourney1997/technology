package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Integer id;

    private String numberPlate;

    private String vehicleModel;

    private String owner;

    private Integer cid;

    private Integer roomid;

    private String phone;

    private String cName;//存放cid对应的企业名

    private String roomName;//存放roomid对应的房间名


}