package com.sunnyxiaobai5.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REGION_AUTH")
public class RegionAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESOURCE_AUTH_ID")
    private ResourceAuth resourceAuth;

    /**
     * 数据权限类型 1：全部，2本人主部门，3：本人所有部门，4：本人，5：自定义，6：无
     */
    @NotNull
    @Column(nullable = false)
    private Byte type = 6;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "REGION_ORG_AUTH",
            joinColumns = {@JoinColumn(name = "REGION_AUTH_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID")}
    )
    private Set<Organization> organizations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceAuth getResourceAuth() {
        return resourceAuth;
    }

    public void setResourceAuth(ResourceAuth resourceAuth) {
        this.resourceAuth = resourceAuth;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionAuth that = (RegionAuth) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
