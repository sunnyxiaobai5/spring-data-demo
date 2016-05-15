package com.sunnyxiaobai5.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RESOURCE_AUTH")
public class ResourceAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 关联的页面授权
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAGE_AUTH_ID")
    private PageAuth pageAuth;

    /**
     * 是否授予全部权限 true：是，false：否
     */
    @NotNull
    @Column(name = "IS_FULL_AUTH", nullable = false)
    private Boolean isFullAuth = false;

    /**
     * 页面功能点关联关系实体
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAGE_RESOURCE_ID")
    private PageResource pageResource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PageAuth getPageAuth() {
        return pageAuth;
    }

    public void setPageAuth(PageAuth pageAuth) {
        this.pageAuth = pageAuth;
    }

    public Boolean getFullAuth() {
        return isFullAuth;
    }

    public void setFullAuth(Boolean fullAuth) {
        isFullAuth = fullAuth;
    }

    public PageResource getPageResource() {
        return pageResource;
    }

    public void setPageResource(PageResource pageResource) {
        this.pageResource = pageResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceAuth that = (ResourceAuth) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
