/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.domain</li>
 * <li>文件名称: Menu.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.domain;

import com.sunnyxiaobai5.common.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Menu")
public class Menu extends BaseEntity {
    /**
     * 菜单名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 是否系统（是否顶级菜单） true：是，false：否
     */
    @Column(name = "IS_SYSTEM")
    private Boolean isSystem;

    /**
     * 菜单对应的路由名称
     */
    @Column(name = "STATE")
    private String state;

    /**
     * 菜单图标 URL
     */
    @Column(name = "ICON_URL")
    private String iconUrl;

    /**
     * 是否快捷菜单 true：是，false：否
     */
    @Column(name = "IS_SHORTCUT_MENU")
    private Boolean isShortcutMenu;

    /**
     * 父菜单
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Menu menu;

    /**
     * 子菜单集合
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID", insertable = false, updatable = false)
    private List<Menu> menuList;

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
