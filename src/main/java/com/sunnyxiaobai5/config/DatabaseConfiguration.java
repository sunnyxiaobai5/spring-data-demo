///*******************************************************************************
// * sunnyxiaobai5@gmail.com
// * <p>
// * <li>项目名称: spring-data-demo</li>
// * <li>完整包名: com.sunnyxiaobai5.config</li>
// * <li>文件名称: DatabaseConfiguration.java</li>
// * <li>内容摘要: </li>
// * <li>内容描述: </li>
// * <li>其他说明: </li>
// * <li>@author Xiangyong Zeng</li>
// ******************************************************************************/
//
//package com.sunnyxiaobai5.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
///**
// * Project: spring-data-demo
// * Package: com.sunnyxiaobai5.config
// * Title:   描述
// * Author:  Xiangyong Zeng
// * Date:    2015/10/13 11:37
// * Version: 1.0
// */
//@Configuration
//@EnableJpaRepositories
//@EnableTransactionManagement
//public class DatabaseConfiguration {
//    @Bean
//    public DataSource dataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        return builder.setType(EmbeddedDatabaseType.HSQL).build();
//    }
//}
