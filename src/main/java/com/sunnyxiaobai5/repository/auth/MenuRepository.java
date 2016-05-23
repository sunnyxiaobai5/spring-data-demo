/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.repository</li>
 * <li>文件名称: MenuRepository.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.repository.auth;

import com.sunnyxiaobai5.common.BaseRepository;
import com.sunnyxiaobai5.domain.auth.Menu;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu, Long> {
    /**
     * 查询所有系统
     *
     * @return
     */
    List<Menu> findByParentIdIsNull();
}
