package com.technologygarden.testdem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class TestController {
    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        return "ttt";
    }

}
