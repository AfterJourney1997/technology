package com.technologygarden.testdem;

import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@ResponseBody
public class TestController {
    @Autowired
    EnterpriseInformationMapper enterpriseInformationMapper;
    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        return "ttt";
    }
    @RequestMapping(value = "/insert")
    public ResultBean insert(Model model){
        EnterpriseInformation ee=new EnterpriseInformation();
        ee.setCName("维他命");
        enterpriseInformationMapper.insertReturnPrimaryKey(ee);
        System.out.println("#######"+ee.getCId());
        return new ResultBean();
    }

}
