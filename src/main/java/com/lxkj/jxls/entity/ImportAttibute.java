package com.lxkj.jxls.entity;

import java.util.List;

/**
 * @Author: ziv
 * @Date: 2018/4/28 09:44
 * @Description: 导入属性
 */

public class ImportAttibute {


    private String tableName;

    private String alias;

    private String title;

    private String row;

    //标题开始位置
    private String startRow;

    //标题结束位置
    private String endRow;

    private List<EntityOneDb> entityOneDbs;


    public ImportAttibute(String tableName, String alias, String title, String row, String startRow, String endRow, List<EntityOneDb> entityOneDbs) {
        this.tableName = tableName;
        this.alias = alias;
        this.title = title;
        this.row = row;
        this.startRow = startRow;
        this.endRow = endRow;
        this.entityOneDbs = entityOneDbs;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getStartRow() {
        return startRow;
    }

    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    public String getEndRow() {
        return endRow;
    }

    public void setEndRow(String endRow) {
        this.endRow = endRow;
    }

    public List<EntityOneDb> getEntityOneDbs() {
        return entityOneDbs;
    }

    public void setEntityOneDbs(List<EntityOneDb> entityOneDbs) {
        this.entityOneDbs = entityOneDbs;
    }
}
