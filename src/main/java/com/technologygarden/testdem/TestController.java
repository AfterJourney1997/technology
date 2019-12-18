package com.technologygarden.testdem;

import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.util.FilUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
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

    @GetMapping("/test")
    public ResponseEntity<byte[]> srad(String fileName, HttpServletRequest request){
        System.out.println("@@@@@@@"+fileName);
        try {
            ResponseEntity<byte[]> responseEntity = FilUploadUtils.downloadFile(fileName);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

}
