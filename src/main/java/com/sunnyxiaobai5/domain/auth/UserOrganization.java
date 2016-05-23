package com.sunnyxiaobai5.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USER_ORGANIZATION")
public class UserOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 用户
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    /**
     * 部门
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;

    /**
     * 部门是否是用户主部门 true：是，false：否
     */
    @NotNull
    @Column(name = "IS_MAIN_ORG", nullable = false)
    private Boolean isMainOrg = false;

    /**
     * 用户是否是部门领导人 true：是，false：否
     */
    @NotNull
    @Column(name = "IS_ORG_LEADER", nullable = false)
    private Boolean isOrgLeader = false;

    /**
     * 是否是历史数据
     */
    @NotNull
    @Column(name = "IS_HISTORY", nullable = false)
    private Boolean isHistory = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Boolean getMainOrg() {
        return isMainOrg;
    }

    public void setMainOrg(Boolean mainOrg) {
        isMainOrg = mainOrg;
    }

    public Boolean getOrgLeader() {
        return isOrgLeader;
    }

    public void setOrgLeader(Boolean orgLeader) {
        isOrgLeader = orgLeader;
    }

    public Boolean getHistory() {
        return isHistory;
    }

    public void setHistory(Boolean history) {
        isHistory = history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrganization that = (UserOrganization) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
