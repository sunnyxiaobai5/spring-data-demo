/**
 * ****************************************************************************
 * Project: spring-data-demo
 * FileName:HomeController.java
 * Author:  Xiangyong Zeng
 * Date:    2015/10/12 16:11
 * Copyright: 2015 www.yineng.com.cn Inc. All rights reserved.
 * Description:
 * ****************************************************************************
 */

package com.sunnyxiaobai5.web.home;

import com.sunnyxiaobai5.domain.User;
import com.sunnyxiaobai5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
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
        return "/index.jsp";
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
