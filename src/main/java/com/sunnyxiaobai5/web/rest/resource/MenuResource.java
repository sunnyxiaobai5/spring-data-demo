/*******************************************************************************
 * Copyright (c) 2005, 2014 www.yineng.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web</li>
 * <li>文件名称: MenuResource.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web.rest.resource;

import com.sunnyxiaobai5.common.JsonBean;
import com.sunnyxiaobai5.service.MenuService;
import com.sunnyxiaobai5.web.rest.dto.MenuDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuResource {

    @Resource
    private MenuService menuService;

    /**
     * 查询单个菜单
     *
     * @param id 菜单ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonBean<MenuDTO> findOne(@PathVariable Long id) {
        return new JsonBean<>(menuService.findOneDTO(id));
    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonBean<List<MenuDTO>> findAll() {
        return new JsonBean<>(menuService.findAllDTO());
    }

    /**
     * 查询某系统下菜单
     *
     * @param id 系统ID
     * @return
     */
    @RequestMapping(value = "findByParentId/{id}", method = RequestMethod.GET)
    public JsonBean<List<MenuDTO>> findByParentId(@PathVariable Long id) {
        return new JsonBean<>(menuService.findByParentId(id));
    }

    /**
     * 查询所有系统
     *
     * @return
     */
    @RequestMapping(value = "findSystem", method = RequestMethod.GET)
    public JsonBean<List<MenuDTO>> findSystem() {
        return new JsonBean<>(menuService.findSystem());
    }
}