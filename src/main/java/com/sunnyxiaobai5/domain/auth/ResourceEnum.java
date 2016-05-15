package com.sunnyxiaobai5.domain.auth;

/**
 * Created by zengxiangyong on 16-5-14.
 */
public enum ResourceEnum {
    ADD((byte) 1, "添加"),
    DELETE((byte) 2, "删除"),
    UPDATE((byte) 3, "修改"),
    RETRIVE((byte) 4, "查询"),
    IMPORT((byte) 5, "导入"),
    EXPORT((byte) 6, "导出");

    private Byte key;
    private String name;

    ResourceEnum(Byte key, String name) {
        this.key = key;
        this.name = name;
    }

    public Byte getkey() {
        return key;
    }

    public void setkey(Byte key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
