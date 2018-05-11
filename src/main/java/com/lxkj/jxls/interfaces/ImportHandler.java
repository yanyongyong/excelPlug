package com.lxkj.jxls.interfaces;

import java.util.List;

/**
 * @Author: ziv
 * @Date: 2018/4/28 09:19
 * @Description: 导入接口
 */
public interface ImportHandler {

    /**
     * 导入Excel表格数据
     * @param tableName 表格名称（在配置文件中已经保存）
     * @param excelPath Excel路径
     */
    String handler( String tableName, String excelPath);


}
