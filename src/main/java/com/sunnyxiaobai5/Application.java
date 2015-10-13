/*******************************************************************************
 * Project: spring-data-demo
 * FileName:Application.java 
 * Author:  Xiangyong Zeng
 * Date:    2015/10/12 16:02
 * Copyright: 2015 www.yineng.com.cn Inc. All rights reserved.
 * Description:
 ******************************************************************************/

package com.sunnyxiaobai5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Project: spring-data-demo
 * Package: com.sunnyxiaobai5.web
 * Title:   描述
 * Author:  Xiangyong Zeng
 * Date:    2015/10/12 16:02
 * Version: 1.0
 */

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.sunnyxiaobai5")
//@ComponentScan
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan (main class 在root package的时候)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
