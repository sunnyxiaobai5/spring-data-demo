package com.sunnyxiaobai5.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunnyxiaobai5.common.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MENU")
public class Menu extends BaseEntity<Long> {
    /**
     * 菜单名称
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * 是否系统（是否顶级菜单） true：是，false：否
     */
    @NotNull
    @Column(name = "IS_SYSTEM", nullable = false)
    private Boolean isSystem = false;

    /**
     * 路由名称
     */
    @Size(min = 1, max = 100)
    @Column(length = 100)
    private String state;

    /**
     * 菜单图标 URL
     */
    @Size(min = 1, max = 100)
    @Column(name = "ICON_URL", length = 100)
    private String iconUrl;

    /**
     * 是否快捷菜单 true：是，false：否
     */
    @NotNull
    @Column(name = "IS_SHORTCUT", nullable = false)
    private Boolean isShortcut = false;

    /**
     * 描述
     */
    @Size(max = 255)
    @Column
    private String description;

    /**
     * 父菜单
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Menu parent;

    /**
     * 子菜单集合
     */
    @JsonIgnore
    @OneToMany(mappedBy = "parent")
    private Set<Menu> children = new HashSet<>();

    /**
     * 菜单对应的页面集合
     */
    @JsonIgnore
    @OneToMany(mappedBy = "menu")
    private Set<Page> pages = new HashSet<>();

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

    public Boolean getIsShortcut() {
        return isShortcut;
    }

    public void setIsShortcut(Boolean isShortcut) {
        this.isShortcut = isShortcut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Set<Menu> getChildren() {
        return children;
    }

    public void setChildren(Set<Menu> children) {
        this.children = children;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        return id.equals(menu.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
