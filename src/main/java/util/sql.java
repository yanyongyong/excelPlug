package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 类　名: demobean
 * 包　名: PACKAGE_NAME
 * 日　期: 2018/4/20 14:42
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 修改人：无
 * 描述：将数据保存到数据库
 */
public class sql {

    static final String DB_URL = "jdbc:mysql://192.168.0.99:3306/towercrane";
    static final String USER = "root";
    static final String PASS = "123456";

    /**
     * @Description:
     *（＾＿＾）☆Created by 16:39 2017/12/26.
     * @param tableName 表名
     * @param sqlPath sql.txt的路径
     */
    public static void createTable(String tableName,String sqlPath){
//        File file = new File(sqlPath);
//        String readSql = readTxtString(file);
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "create table if not exists "+ tableName + sqlPath ;
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


    public static void main(String[] args){
//        String path = getProjectPath();
//        System.out.println(path);
//        File file = new File("D:\\workSpace\\SmartWorksite\\TowerCrane\\src\\main\\java\\com\\hxjd\\towercrane\\sql\\CycleData.sql");
//        System.out.println(file);
    }
}
