package com.lxkj.jxls.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxkj.jxls.core.ImportImp;
import com.lxkj.jxls.entity.EntityOneDb;
import com.lxkj.jxls.entity.ImportAttibute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Author: ziv
 * @Date: 2018/5/2 11:27
 * @Description: Excel导入Dao
 */
public class ImportDao {

    static Logger logger = LoggerFactory.getLogger(ImportImp.class);

    static final String DB_URL = "jdbc:mysql://localhost:3306/excelplug?serverTimezone=UTC&useSSL=false";
    static final String USER = "root";
    static final String PASS = "123456";

    /**
     * @Description: 创建表
     * @param importAttibute 对象
     */
    public void creatTableFromJson(ImportAttibute importAttibute){
        String sql = "create table if not exists `" + importAttibute.getTableName() +"` (`id` int(11) NOT NULL AUTO_INCREMENT,";
        sql = getTabelJson(importAttibute,sql);
        sql = sql + "`addTime` VARCHAR(255), PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
        logger.info(sql);
        excute(sql);
    }


    private String getTabelJson(ImportAttibute importAttibute,String sql) {
        for (EntityOneDb entityOneDb : importAttibute.getEntityOneDbs()){
            sql +=  "`" + entityOneDb.getDbName()+"` VARCHAR(255) COMMENT '"+entityOneDb.getNote()+"',";
        }
        return sql;
    }


    public void addDataDao(ImportAttibute importAttibute, List<Object> objects){
        String fieldName = "";
        String vaules = "";
        for (EntityOneDb entityOneDb : importAttibute.getEntityOneDbs()){
            fieldName += entityOneDb.getDbName() + "," ;
        }
        for (Object obj : objects){
            String vaule = "(";
            String s = JSON.toJSONString(obj);
            JSONObject jso = JSON.parseObject(s);
            for (EntityOneDb ex : importAttibute.getEntityOneDbs()){
                String data = jso.getString(ex.getEntityName());
                vaule +=  "'"+data+"',";
            }
            vaule = vaule.substring(0,vaule.length()-1);
            vaule += "),";
            vaules += vaule;
        }
        vaules = vaules.substring(0,vaules.length()-1);
        vaules += ";";
        fieldName = fieldName.substring(0,fieldName.length()-1);
        String sql = "insert into "+ importAttibute.getTableName() +"("+fieldName+") VALUES "+vaules+" ";
        excute(sql);
    }

    /**
     * @Description: 执行sql语句
     * @param  sqls sql语句
     */
    public void excute(String sqls){
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = sqls;
            System.out.println("第二个："+sql);
            stmt.executeUpdate(sql);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null){
                    conn.close();
                }
            }catch(SQLException se){}
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }


}
