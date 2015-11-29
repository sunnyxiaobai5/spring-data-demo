/*******************************************************************************
 * Copyright (c) 2005, 2014 www.yineng.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: MenuServiceImpl.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service;

import com.sunnyxiaobai5.common.BaseRepository;
import com.sunnyxiaobai5.common.BaseServiceImpl;
import com.sunnyxiaobai5.domain.Menu;
import com.sunnyxiaobai5.repository.MenuRepository;
import com.sunnyxiaobai5.web.rest.dto.MenuDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Transactional
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuDTO, Long> implements MenuService {
    @Resource
    private MenuRepository menuRepository;

    @Override
    protected BaseRepository<Menu, Long> getBaseRepository() {
        return menuRepository;
    }

    @Override
    public List<MenuDTO> findByParentId(Long id) {

        List<Menu> menuList = menuRepository.findOne(id).getMenuList();

        return fromEntity(menuList);

    }
}
