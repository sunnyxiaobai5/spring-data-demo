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

import java.util.ArrayList;
import java.util.List;

public class RedisUtils {
    private RedisUtils() {
    }

    public static String getRedisKey(String prefix, String identity) {
        return SecurityUtils.getCurrentLogin() + prefix;
    }

    public static void listLeftPush(String key, Object o) {
    }

    public static void listRightPush(String key, Object o) {

    }

    public static <T> T listLeftPop(String key) {
        return null;
    }

    public static <T> T listRightPop(String key) {
        return null;
    }

    public static <T> List<T> listPopAll(String key) {
        return new ArrayList<>();
    }
}
