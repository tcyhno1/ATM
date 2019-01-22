package com.yuhao.atmboot.controller;


import com.yuhao.atmboot.entity.User;
import com.yuhao.atmboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @RequestMapping("toLogin")
    public String toLogin(String name){

        User user = new User();
        user.setUsername(name);
        user.setPwd("1234");
        user.setCreatetiime(new Date());
        user.setModifytime(new Date());
        userMapper.insert(user);

        return "login";
    }


}
