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
public class PolicyRelated {

    private Integer prId;
    @NotBlank
    private String prTitle;
    @NotNull
    private Integer prLevel;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date prTime;
    private String prFile;
    @NotBlank
    private String prContent;
    private MultipartFile file = null;

}