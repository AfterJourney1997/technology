package com.technologygarden.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class InputDescription {
    @Value("${InputDescription.Dname}")
    private String Dname;
    @Value("${InputDescription.Dcategory}")
    private String Dcategory;
    @Value("${InputDescription.Ddate}")
    private String Ddate;
    @Value("${InputDescription.Dcode}")
    private String Dcode;
    @Value("${InputDescription.DlegalPerson}")
    private String DlegalPerson;
    @Value("${InputDescription.Dsex}")
    private String Dsex;
    @Value("${InputDescription.Deducation}")
    private String Deducation;
    @Value("${InputDescription.Drank}")
    private String Drank;
    @Value("${InputDescription.Dmoney}")
    private String Dmoney;
    @Value("${InputDescription.Dproduct}")
    private String Dproduct;
    @Value("${InputDescription.Demployee}")
    private String Demployee;
    @Value("${InputDescription.Dfile}")
    private String Dfile;

}
