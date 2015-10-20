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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

//        SpringApplication app = new SpringApplication(Application.class);

//        Sets if the Spring banner should be displayed when the application runs. Defaults to true.
//        设置启动时是否显示Spring banner（命令行最开始部分）,默认为显示
//        若在properties文件中配置spring.main.show-banner=true/false，将以properties文件配置为准
//        app.setShowBanner(true);
//        app.run(args);
    }
}
