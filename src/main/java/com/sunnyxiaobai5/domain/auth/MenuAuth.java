package com.sunnyxiaobai5.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MENU_AUTH")
public class MenuAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 所授权角色
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    /**
     * 分配的菜单
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    /**
     * 是否授予全部权限 true：是，false：否
     */
    @NotNull
    @Column(name = "IS_FULL_AUTH", nullable = false)
    private Boolean isFullAuth = false;

    /**
     * 分配类型 1：按角色分配，2：按账户分配
     */
    @NotNull
    @Column(nullable = false)
    private Byte type = 1;

    /**
     * 所授权账户
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Boolean getFullAuth() {
        return isFullAuth;
    }

    public void setFullAuth(Boolean fullAuth) {
        isFullAuth = fullAuth;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuAuth menuAuth = (MenuAuth) o;

        return id.equals(menuAuth.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
