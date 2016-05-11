/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.config</li>
 * <li>文件名称: HibernateNamingStrategy.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.config;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class HibernateNamingStrategy extends ImprovedNamingStrategy {
    @Override
    public String tableName(String tableName) {
        return super.tableName(tableName).toUpperCase();
    }
}
