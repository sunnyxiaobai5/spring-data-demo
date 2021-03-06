/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.repository</li>
 * <li>文件名称: UserRepository.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.repository.auth;

import com.sunnyxiaobai5.common.BaseRepository;
import com.sunnyxiaobai5.domain.auth.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    User findByName(String name);

    Optional<User> findOneByName(String name);
}
