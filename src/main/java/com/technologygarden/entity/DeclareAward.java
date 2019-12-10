package com.technologygarden.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeclareAward {
    private Integer dId;

    private String dName;

    private Integer aId;

    private String filename;

    private int dStatus;

    private Integer cId;

    private Role role=null;

    private String aName;

    private String filePath;

    private MultipartFile blFile;

}