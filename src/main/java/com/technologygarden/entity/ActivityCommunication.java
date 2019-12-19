package com.technologygarden.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCommunication {

    private Integer acomId;
    private String acomName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date acomTime;
    private Integer acomCategoryId;
    private String acomPlace;
    private String acomPeople;
    private String acomFile;
    private ActivityCategory activityCategory = null;

}