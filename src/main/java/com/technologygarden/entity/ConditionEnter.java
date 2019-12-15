package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConditionEnter {

    private Integer ceId;
    @NotBlank
    private String ceTitle;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ceTime;
    private String ceFile;
    @NotBlank
    private String ceContent;

}