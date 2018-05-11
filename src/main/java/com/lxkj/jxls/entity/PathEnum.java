package com.lxkj.jxls.entity;

/**
 * @Author: ziv
 * @Date: 2018/4/28 11:17
 * @Description:路径
 */
public enum PathEnum {

    TEMPLATE_PATH("src/main/java/","模板生成路径"),

    CONFIGURATION_PATH("src/main/java/","配置文件生成路径"),

    CONFIG_NAME("excelConfig","配置文件名");



    private String path ;
    private String explain;

    PathEnum(String path, String explain) {
        this.path = path;
        this.explain = explain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
