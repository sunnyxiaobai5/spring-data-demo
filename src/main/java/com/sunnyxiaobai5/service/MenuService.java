/*******************************************************************************
 * Copyright (c) 2005, 2014 www.yineng.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: MenuService.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service;

import com.sunnyxiaobai5.common.BaseService;
import com.sunnyxiaobai5.domain.Menu;
import com.sunnyxiaobai5.web.rest.dto.MenuDTO;

import java.util.List;

public interface MenuService extends BaseService<Menu, MenuDTO, Long> {

    /**
     * 查询某系统下菜单
     *
     * @param id 系统ID
     * @return
     */
    List<MenuDTO> findByParentId(Long id);

    /**
     * 查询所有系统
     *
     * @return
     */
    List<MenuDTO> findSystem();

}
