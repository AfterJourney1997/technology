package com.technologygarden.entity;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRoomDevice {

    private Integer crdId;
    private Integer crdCompanyId;
    private Integer crdRoomId;
    private Integer crdDeviceId;
    private Integer crdNumber;
    private Room room = null;
    private Device device = null;

}