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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.sql.Savepoint;
import java.util.List;

public interface BaseService<T extends BaseEntity, K extends BaseDTO, ID extends Serializable> {

    T convert(K k);

    K convert(T t);

    List<T> fromDTO(List<K> kList);

    List<K> fromEntity(List<T> tList);

    T save(T t);

    T save(K k);

    List<T> saveAll(List<T> tList);

    List<T> saveAllDTO(List<K> kList);

    T findOne(ID id);

    K findOneDTO(ID id);

    List<T> findAll();

    List<K> findAllDTO();

    List<T> findAll(List<ID> idList);

    List<K> findAllDTO(List<ID> idList);

    Page<T> findAll(Pageable pageable);

    Page<K> findAllDTO(Pageable pageable);

    void delete(ID id);

    void delete(T entity);

    void deleteAllByID(List<ID> idList);

    void deleteAll(List<T> tList);
}
