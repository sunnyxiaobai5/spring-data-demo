/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: MenuServiceImpl.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service.auth;

import com.sunnyxiaobai5.common.BaseRepository;
import com.sunnyxiaobai5.common.BaseServiceImpl;
import com.sunnyxiaobai5.domain.auth.Menu;
import com.sunnyxiaobai5.repository.auth.MenuRepository;
import com.sunnyxiaobai5.web.rest.dto.MenuDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuDTO, Long> implements MenuService {
    @Resource
    private MenuRepository menuRepository;

    @Override
    protected BaseRepository<Menu, Long> getRepository() {
        return menuRepository;
    }

    @Override
    public List<MenuDTO> findModules(Long id) {

        Set<Menu> menus = menuRepository.findOne(id).getChildren();

        return menus.parallelStream().sequential().map(menu -> {
            MenuDTO menuDTO = this.fromEntity(menu);
            menuDTO.setMenuDTOs(fromEntity(menu.getChildren().stream().collect(Collectors.toList())));
            return menuDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<MenuDTO> findSystems() {

        List<Menu> menuList = menuRepository.findByParentIdIsNull();

        return fromEntity(menuList);
    }
}
