/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web.rest.dto</li>
 * <li>文件名称: UserDTO.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunnyxiaobai5.common.BaseDTO;

public class UserDTO extends BaseDTO<Long> {
    /**
     * 用户名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 性别 1：男，2：女
     */
    private Byte genderCode;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 用户对应的账户
     */
    private AccountDTO accountDTO;

    /**
     * 是否逻辑删除
     */
    @JsonIgnore
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

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
