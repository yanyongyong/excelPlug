import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.junit.jupiter.api.Test;
import primary.EntityTest;
import util.CglibBean;
import util.ExcelRowColformat;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;

import static primary.Import.readExcelData;
import static util.CglibBean.createBean;
import static util.CreateEntity.entitys;
//import static util.CreateEntity.createBean;

/**
 * 类　名: demobean
 * 包　名: PACKAGE_NAME
 * 日　期: 2018/4/20 14:42
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 修改人：无
 */
public class demobean {


//    public void improtData(String packageOutPath,String entityName,List<ExcelRowColformat> ercfs,String excelPath) throws IOException {
//        String[] colnames = new String[ercfs.size()];
//        String[] colTypes = new String[ercfs.size()];
//        for (int i = 0; i < ercfs.size(); i++){
//            colnames[i] = ercfs.get(i).getEntityFiledName();
//            colTypes[i] = "String";
//        }
//        entitys(packageOutPath,entityName,colnames,colTypes);
//
//    }

    public void improtData(List<ExcelRowColformat> ercfs,String excelPath) throws IOException {


    }

    /**
     * 创建表
     * @param excelRowColformats
     * @param tableName
     */
    public void creatTable(List<ExcelRowColformat> excelRowColformats,String tableName){
        String sql = "CREATE TABLE ` " + tableName +" ` (`id` int(11) NOT NULL AUTO_INCREMENT,";
        for (ExcelRowColformat e : excelRowColformats){
            sql = sql + "`" +e.getEntityFiledName()+"` VARCHAR(255) COMMENT,";
        }
        sql = sql + "PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
    }

    @Test
    public <T> void testImportExcelData() throws ClassNotFoundException{
        EntityTest entity = new EntityTest();
        // Excel列数据和实体字段一一对应
        List<ExcelRowColformat> list = new ArrayList<>();
        //获取从第一行（除去标题的第一行）开始第二列的所有数据
        list.add(new ExcelRowColformat("1","1","name"));
        list.add(new ExcelRowColformat("1","2","sex"));

        CglibBean bean = createBean(list);
        // 获得bean的实体
        Object object = bean.getObject();
        //提取EXCEL文件信息
        List<Object> objects = readExcelData(object,"1","4",list,"E:\\2017.xls");

        for (Object o : objects){
            String s = JSON.toJSONString(o);
            JSONObject jso = JSON.parseObject(s);
            String vString=jso.getString("sex");
            System.out.println(vString);
        }
        System.out.println(objects.size()+":...................");
    }



}
