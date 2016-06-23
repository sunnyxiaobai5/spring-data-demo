package com.sunnyxiaobai5.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunnyxiaobai5.common.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
@Where(clause = "IS_DELETE=0")
public class User extends BaseEntity<Long> {
    /**
     * 用户名称
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50)
    private String name;

    /**
     * 描述
     */
    @Size(max = 255)
    @Column
    private String description;

    /**
     * 性别 1：男，2：女
     */
    @NotNull
    @Column(name = "GENDER_CODE")
    private Byte genderCode;

    /**
     * 电子邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 用户对应的账户
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    @JsonIgnore
    private Account account;

    /**
     * 是否逻辑删除
     */
    @NotNull
    @JsonIgnore
    @Column(name = "IS_DELETE", nullable = false)
    private Boolean isDelete = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(Byte genderCode) {
        this.genderCode = genderCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
