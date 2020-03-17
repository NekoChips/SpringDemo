package com.demo.sso.auth.constants;

/**
 * @author Yangjie.Chen
 * @description 公共常量类
 * @date 2020/3/11
 */
public enum CommonConstant {
    EXPIRED(-1), LOCKED(0), VALID(1);

    private final int value;

    CommonConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
