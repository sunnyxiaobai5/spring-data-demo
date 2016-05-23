package com.sunnyxiaobai5.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PAGE")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 页面名称
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private Long name;

    /**
     * 页面对应的路由名称
     */
    @Size(min = 1, max = 100)
    @Column(name = "STATE", length = 100)
    private String state;

    /**
     * 描述
     */
    @Size(max = 255)
    @Column
    private String description;

    /**
     * 所属菜单
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    /**
     * 页面对应的功能点关系集合
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
    private Set<PageResource> pageResources = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Set<PageResource> getPageResources() {
        return pageResources;
    }

    public void setPageResources(Set<PageResource> pageResources) {
        this.pageResources = pageResources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        return id.equals(page.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
