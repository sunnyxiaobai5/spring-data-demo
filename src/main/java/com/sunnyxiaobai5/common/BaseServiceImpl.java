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
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<T extends BaseEntity, K extends BaseDTO, ID extends Serializable> implements BaseService<T, K, ID> {

    protected abstract BaseRepository<T, ID> getBaseRepository();

    protected Class<T> tClass;

    protected Class<K> kClass;

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

//        List<T> tList = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(kList)) {
//            kList.parallelStream().sequential().forEach(t -> tList.add(convert(t)));
//        }
//        return tList;
    }

    @Override
    public List<K> fromEntity(List<T> tList) {
        return new ArrayList<>(tList.stream().map(this::fromEntity).collect(Collectors.toCollection(ArrayList::new)));

//        List<K> kList = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(tList)) {
//            tList.parallelStream().sequential().forEach(t -> kList.add(convert(t)));
//        }
//        return kList;
    }

    @Override
    public T findOne(ID id) {
        return getBaseRepository().findOne(id);
    }

    @Override
    public K findOneDTO(ID id) {
        return fromEntity(getBaseRepository().findOne(id));
    }

    @Override
    public List<T> findAll() {
        return getBaseRepository().findAll();
    }

    @Override
    public List<K> findAllDTO() {
        return fromEntity(getBaseRepository().findAll());
    }

    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public T save(K k) {
        return null;
    }

    @Override
    public List<T> saveAll(List<T> tList) {
        return null;
    }

    @Override
    public List<T> saveAllDTO(List<K> kList) {
        return null;
    }

    @Override
    public List<T> findAll(List<ID> ids) {
        return null;
    }

    @Override
    public List<K> findAllDTO(List<ID> ids) {
        return null;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<K> findAllDTO(Pageable pageable) {
        return null;
    }

    @Override
    public void delete(ID id) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteAllByID(List<ID> ids) {

    }

    @Override
    public void deleteAll(List<T> tList) {

    }
}
