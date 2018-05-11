package com.lxkj.jxls.entity;

/**
 * @Author: ziv
 * @Date: 2018/4/28 09:44
 * @Description: 实体对应数据库字段
 */
public class EntityOneDb {

    private String col;

    private String note;

    private String entityName;

    private String dbName;

    public EntityOneDb(String col, String note, String entityName, String dbName) {
        this.col = col;
        this.note = note;
        this.entityName = entityName;
        this.dbName = dbName;
    }

    public EntityOneDb() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
