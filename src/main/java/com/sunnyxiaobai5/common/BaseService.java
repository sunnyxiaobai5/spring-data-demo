/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.common</li>
 * <li>文件名称: BaseService.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.common;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseEntity, K extends BaseDTO, ID extends Serializable> {

    T findOne(ID id);

    K findOneDTO(ID id);

    List<T> findAll();

    List<K> findAllDTO();

    T convert(K k);

    K convert(T t);

    List<T> fromDTO(List<K> kList);

    List<K> fromEntity(List<T> tList);

}
