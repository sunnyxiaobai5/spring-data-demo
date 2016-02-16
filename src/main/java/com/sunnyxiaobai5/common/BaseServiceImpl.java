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
    public T convert(K k) {
        T t = null;
        try {
            t = tClass.newInstance();
            BeanUtils.copyProperties(t, k);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public K convert(T t) {
        K k= null;
        try {
            k = kClass.newInstance();
            BeanUtils.copyProperties(k, t);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return k;
    }

    @Override
    public List<T> fromDTO(List<K> kList) {
        return new ArrayList<>(kList.stream().map(k -> convert(k)).collect(Collectors.toCollection(ArrayList::new)));

//        List<T> tList = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(kList)) {
//            kList.parallelStream().sequential().forEach(t -> tList.add(convert(t)));
//        }
//        return tList;
    }

    @Override
    public List<K> fromEntity(List<T> tList) {
        return new ArrayList<>(tList.stream().map(t -> convert(t)).collect(Collectors.toCollection(ArrayList::new)));

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
        return convert(getBaseRepository().findOne(id));
    }

    @Override
    public List<T> findAll() {
        return (List<T>) getBaseRepository().findAll();
    }

    @Override
    public List<K> findAllDTO() {
        return fromEntity((List<T>) getBaseRepository().findAll());
    }
}
