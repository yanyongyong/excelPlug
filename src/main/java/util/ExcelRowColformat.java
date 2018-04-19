package util;

/**
 * 类　名: ExcelRowColformat
 * 包　名: common.utils
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/16 14:43
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 描　述:
 */
public class ExcelRowColformat {

    /**
     * 行
     */
    private String row;

    /**
     * 列
     */
    private String col;

    /**
     * 实体字段名
     */
    private String entityFiledName;

    public ExcelRowColformat(String row, String col, String entityFiledName) {
        this.row = row;
        this.col = col;
        this.entityFiledName = entityFiledName;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getEntityFiledName() {
        return entityFiledName;
    }

    public void setEntityFiledName(String entityFiledName) {
        this.entityFiledName = entityFiledName;
    }
}
