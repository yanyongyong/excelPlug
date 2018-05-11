# excelPlug
基于JXLS的Excel导入导出插件
1.字段数不能超过26;  
2.template包不能删除,生成的xls模板放在此包下(src\main\java\template);  
3.多个sheet的Excel只能导出第一个；  
4.支持一键导入到数据库（Mysql），连接配置在sql.java;  
5.导入功能第一次生成xml文件时，存在小Bug；  
6.调用实例请看：demo.java
  
下一版本：  
1.支持多个sheet导入；  
2.支持导出到多个sheet;
