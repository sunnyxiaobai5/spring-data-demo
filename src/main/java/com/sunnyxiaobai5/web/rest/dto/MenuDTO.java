/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web.rest.dto</li>
 * <li>文件名称: MenuDTO.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web.rest.dto;

import com.sunnyxiaobai5.common.BaseDTO;
import com.sunnyxiaobai5.common.annotation.ExportAnnotation;

import java.util.List;

public class MenuDTO extends BaseDTO<Long> {

    @ExportAnnotation(name = "菜单名称")
    private String name;

    @ExportAnnotation(name = "是否系统")
    private Boolean isSystem;

    @ExportAnnotation(name = "路由状态")
    private String state;

    @ExportAnnotation(name = "icon路径")
    private String iconUrl;

    @ExportAnnotation(name = "是否快捷菜单")
    private Boolean isShortcutMenu;

    private MenuDTO menuDTO;

    private List<MenuDTO> menuDTOList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Boolean getIsShortcutMenu() {
        return isShortcutMenu;
    }

    public void setIsShortcutMenu(Boolean isShortcutMenu) {
        this.isShortcutMenu = isShortcutMenu;
    }

    public MenuDTO getMenuDTO() {
        return menuDTO;
    }

    public void setMenuDTO(MenuDTO menuDTO) {
        this.menuDTO = menuDTO;
    }

    public List<MenuDTO> getMenuDTOList() {
        return menuDTOList;
    }

    public void setMenuDTOList(List<MenuDTO> menuDTOList) {
        this.menuDTOList = menuDTOList;
    }
}
