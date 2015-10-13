/*******************************************************************************
 * Project: spring-data-demo
 * FileName:DatabaseConfiguration.java 
 * Author:  Xiangyong Zeng
 * Date:    2015/10/13 11:37
 * Copyright: 2015 www.yineng.com.cn Inc. All rights reserved.
 * Description:
 ******************************************************************************/

package com.sunnyxiaobai5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Project: spring-data-demo
 * Package: com.sunnyxiaobai5.config
 * Title:   描述
 * Author:  Xiangyong Zeng
 * Date:    2015/10/13 11:37
 * Version: 1.0
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class DatabaseConfiguration {
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }
}
