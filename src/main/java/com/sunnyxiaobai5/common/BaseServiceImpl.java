/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.common</li>
 * <li>文件名称: BaseServiceImpl.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.common;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<T extends BaseEntity, K extends BaseDTO, ID extends Serializable> implements BaseService<T, K, ID> {

    protected abstract BaseRepository<T, ID> getRepository();

    private Class<T> tClass;

    private Class<K> kClass;

    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        this.tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.kClass = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public T fromDTO(K k) {
        T t = null;
        try {
            t = tClass.newInstance();
            BeanUtils.copyProperties(t, k);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public K fromEntity(T t) {
        K k = null;
        try {
            k = kClass.newInstance();
            BeanUtils.copyProperties(k, t);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return k;
    }

    @Override
    public List<T> fromDTO(List<K> kList) {
        return new ArrayList<>(kList.stream().map(this::fromDTO).collect(Collectors.toCollection(ArrayList::new)));
    }

    @Override
    public List<K> fromEntity(List<T> tList) {
        return new ArrayList<>(tList.stream().map(this::fromEntity).collect(Collectors.toCollection(ArrayList::new)));
    }

    @Override
    public T findOne(ID id) {
        return getRepository().findOne(id);
    }

    @Override
    public K findOneDTO(ID id) {
        return fromEntity(getRepository().findOne(id));
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<K> findAllDTO() {
        return fromEntity(getRepository().findAll());
    }

    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    public T save(K k) {
        return getRepository().save(fromDTO(k));
    }

    @Override
    public List<T> saveAll(List<T> tList) {
        return getRepository().save(tList);
    }

    @Override
    public List<T> saveAllDTO(List<K> kList) {
        return getRepository().save(fromDTO(kList));
    }

    @Override
    public List<T> findAll(List<ID> ids) {
        return getRepository().findAll(ids);
    }

    @Override
    public List<K> findAllDTO(List<ID> ids) {
        return fromEntity(getRepository().findAll(ids));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        List<T> tList = getRepository().findAll();
        return new PageImpl<>(tList, pageable, tList.size());
    }

    @Override
    public Page<K> findAllDTO(Pageable pageable) {
        List<T> tList = getRepository().findAll();
        return new PageImpl<>(fromEntity(tList), pageable, tList.size());
    }

    @Override
    public void delete(ID id) {
        getRepository().delete(id);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteAllByID(List<ID> ids) {
        ids.stream().forEach(id -> getRepository().delete(id));
    }

    @Override
    public void deleteAll(List<T> tList) {
        getRepository().delete(tList);
    }
}
