package com.lxkj.jxls.entity;

/**
 * @Author: ziv
 * @Date: 2018/5/2 15:20
 * @Description: 错误码
 */
public enum MessageCode {

    SYSTEM(500, "系统错误"),
    PARAMETER_CHECK(808, "无此Excel模型相关信息,请先添加"),
    ALERDY_EXISTED(100,"此表格名已经存在"),
    SUCCESS(200,"成功");

    private final Integer value;
    private final String message;

    MessageCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public String getCode() {
        return value.toString();
    }

    public static MessageCode getByCode(Integer value) {
        for (MessageCode _enum : values()) {
            if (_enum.getValue() == value) {
                return _enum;
            }
        }
        return null;
    }

}
