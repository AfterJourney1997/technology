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
public class ActivityIncubation {

    private Integer aiId;
    @NotBlank
    private String aiName;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date aiTime;
    @NotNull
    private Integer aiCategoryId;
    @NotNull
    private Integer aiPeopleNumber;
    private String aiPic;
    private String aiFile;
    @NotBlank
    private String aiContent;
    private ActivityCategory activityCategory = null;
    private MultipartFile[] multipartFileArray = null;

}