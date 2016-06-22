package com.sunnyxiaobai5.domain.auth;

/**
 * Created by zengxiangyong on 16-5-14.
 */
public enum ResourceEnum {
    ADD((byte) 1, "添加"),
    DELETE((byte) 2, "删除"),
    UPDATE((byte) 3, "修改"),
    QUERY((byte) 4, "查询"),
    IMPORT((byte) 5, "导入"),
    EXPORT((byte) 6, "导出"),
    PRINT((byte) 7, "打印"),
    VIEW((byte) 8, "查看");

    private Byte key;
    private String name;

    ResourceEnum(Byte key, String name) {
        this.key = key;
        this.name = name;
    }

    public Byte getkey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
