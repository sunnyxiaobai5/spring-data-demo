package com.sunnyxiaobai5.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PAGE_AUTH")
public class PageAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 关联的菜单授权
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_AUTH_ID")
    private MenuAuth menuAuth;

    /**
     * 是否授予全部权限 true：是，false：否
     */
    @NotNull
    @Column(name = "IS_FULL_AUTH", nullable = false)
    private Boolean isFullAuth = false;

    /**
     * 分配的页面
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAGE_ID")
    private Page page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuAuth getMenuAuth() {
        return menuAuth;
    }

    public void setMenuAuth(MenuAuth menuAuth) {
        this.menuAuth = menuAuth;
    }

    public Boolean getFullAuth() {
        return isFullAuth;
    }

    public void setFullAuth(Boolean fullAuth) {
        isFullAuth = fullAuth;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageAuth pageAuth = (PageAuth) o;

        return id.equals(pageAuth.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
