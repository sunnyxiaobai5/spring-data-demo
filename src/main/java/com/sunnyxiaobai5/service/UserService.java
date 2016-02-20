/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: UserService.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service;

import com.sunnyxiaobai5.common.BaseService;
import com.sunnyxiaobai5.domain.User;
import com.sunnyxiaobai5.web.rest.dto.UserDTO;

public interface UserService extends BaseService<User, UserDTO, Long> {
}
