/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web.rest.mapper</li>
 * <li>文件名称: UserMapper.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web.rest.mapper;

import com.sunnyxiaobai5.domain.auth.User;
import com.sunnyxiaobai5.web.rest.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    List<UserDTO> userToUserDTO(List<User> users);
}

