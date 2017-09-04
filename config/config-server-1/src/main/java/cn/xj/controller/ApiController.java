package cn.xj.controller;

import cn.xj.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private UserInfo userInfo;

    @RequestMapping("/name")
    public String getConsulInfo() {
        System.out.println(userInfo.getName());
        return userInfo.getName();
    }

}
