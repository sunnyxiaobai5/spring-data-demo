/*******************************************************************************
 * Copyright (c) 2005, 2014 www.yineng.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web.home</li>
 * <li>文件名称: Xiangyong Zeng</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web;

import com.sunnyxiaobai5.domain.User;
import com.sunnyxiaobai5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Project: spring-data-demo
 * Package: com.sunnyxiaobai5.web.home
 * Title:   描述
 * Author:  Xiangyong Zeng
 * Date:    2015/10/12 16:11
 * Version: 1.0
 */
@Controller
//@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/homePage")
    String homePage() {
        return "/index.html";
    }

    @RequestMapping("/homeInfo")
    @ResponseBody
    String homeInfo() {
        return "home info String";
    }


    @RequestMapping("/home")
    @ResponseBody
    String home() {
        return "hello home";
    }

    @RequestMapping("/getByName")
    @ResponseBody
    public String getByName(String name) {
        String userId;
        User user = userRepository.findByName(name);
        if (!StringUtils.isEmpty(user)) {
            userId = String.valueOf(user.getId());
            return "this user id is " + userId;
        }
        return "not exists";
    }
}
