/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: UserServiceImpl.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service.auth;

import com.sunnyxiaobai5.common.BaseRepository;
import com.sunnyxiaobai5.common.BaseServiceImpl;
import com.sunnyxiaobai5.domain.auth.User;
import com.sunnyxiaobai5.repository.auth.UserRepository;
import com.sunnyxiaobai5.web.rest.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, UserDTO, Long> implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    protected BaseRepository<User, Long> getBaseRepository() {
        return userRepository;
    }
}
