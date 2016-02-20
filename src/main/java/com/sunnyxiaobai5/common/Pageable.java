/*******************************************************************************
 * @project: spring-data-demo
 * @package: com.sunnyxiaobai5.common
 * @author: Zeng Xiangyong
 * @date: 2016/2/20 10:53
 * @copyright: 2016 www.yineng.com.cn Inc. All rights reserved.
 * @version: V1.0
 ******************************************************************************/
package com.sunnyxiaobai5.common;

import org.springframework.data.domain.Sort;

/**
 * @author Zeng Xiangyong
 * @ClassName Pageable
 * @Description 类描述
 * @date 2016/2/20
 */
public class Pageable extends org.springframework.data.domain.AbstractPageRequest {

    public Pageable(int page, int size) {
        super(page, size);
    }

    @Override
    public org.springframework.data.domain.Pageable next() {
        return new Pageable(getPageNumber() + 1, getPageSize());
    }

    /**
     * Returns the {@link com.sunnyxiaobai5.common.Pageable} requesting the previous {@link Page}.
     *
     * @return
     */
    @Override
    public org.springframework.data.domain.Pageable previous() {
        return getPageNumber() == 0 ? this : new Pageable(getPageNumber() - 1, getPageSize());
    }

    @Override
    public org.springframework.data.domain.Pageable first() {
        return new Pageable(0, getPageSize());
    }

    /**
     * Returns the sorting parameters.
     *
     * @return
     */
    @Override
    public Sort getSort() {
        return null;
    }
}
