/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.util</li>
 * <li>文件名称: PdfTableUtils.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.util;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

public class RedisUtils {

    @Resource
    private static RedisTemplate<String, String> template;

    @Resource(name = "redisTemplate")
    private static ListOperations<String, Object> listOps;

    private RedisUtils() {
    }

    public static String getRedisKey(String prefix, String identity) {
        return SecurityUtils.getCurrentLogin() + "_" + prefix + "_" + identity;
    }

    public static <T> void listLeftPush(String key, T o) {
        listOps.leftPush(key, o);
    }

    public static <T> void listRightPush(String key, T o) {
        listOps.rightPush(key, o);
    }

    @SuppressWarnings("unchecked")
    public static <T> T listLeftPop(String key) {
        return (T) listOps.<T>leftPop(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T listRightPop(String key) {
        return (T) listOps.rightPop(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> listPopAll(String key) {
        return (List<T>) listOps.range(key, 0, -1);
    }

    public static void delete(String key) {
        template.delete(key);
    }
}
