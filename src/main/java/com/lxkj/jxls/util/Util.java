package com.lxkj.jxls.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxkj.jxls.entity.EntityOneDb;
import com.lxkj.jxls.entity.ImportAttibute;
import com.lxkj.jxls.entity.PathEnum;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static com.lxkj.jxls.entity.PathEnum.CONFIGURATION_PATH;

/**
 * @Author: ziv
 * @Date: 2018/4/28 10:46
 * @Description: 工具类
 */
public class Util {

    /**
     * json转对象
     */
    public static ImportAttibute JsonToObject(String onejsonData){
        return JSON.parseObject(onejsonData,ImportAttibute.class);
    }

    /**
     * 判断配置文件中是否有tableName的信息
     * @return 表格存在返回表格Json配置数据，否则返回null
     */
    public static String tableNameIsExist(String json,String tableName){
        JSONArray jso = JSONArray.parseArray(json);
        System.out.println(jso.size());
        for (int i = 0; i < jso.size(); i++){
            JSONObject object = JSONObject.parseObject(jso.getString(i));
            if (object.getString("tableName").equals(tableName)){
                return JSON.toJSONString(object);
            }
        }
        return null;
    }

    @Test
    public void test(){
        updataJson("excelConfig","table_test2");
    }

    public void updataJson(String fileName,String tableName){
        BufferedReader bre = null;
        OutputStreamWriter pw = null;
        String str = "";
        try {
            String file = PathEnum.CONFIGURATION_PATH.getPath() + fileName + ".properties";
            String backupsFile = PathEnum.CONFIGURATION_PATH.getPath()+"backups.properties";
            bre = new BufferedReader(new FileReader(file));
            pw = new OutputStreamWriter(new FileOutputStream(backupsFile),"UTF-8");
            while ((str = bre.readLine()) != null) {
                if (str.indexOf(tableName) < 0) {
                    pw.write(str+"\r\n");
                }
            }
            bre.close();
            pw.close();
            copyFileUsingJava7Files(new File(backupsFile),new File(file));
        }catch (Exception e){

        }
    }


    /**
     * 复制文件
     * @param source
     * @param dest
     * @throws IOException
     */
    private static void copyFileUsingJava7Files(File source, File dest)
            throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            if(is != null) {
                is.close();
            }
            if(os != null) {
                os.close();
            }
        }
    }

    /**
     * 删除单个文件
     * @param Path 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String Path) {
        Boolean flag = false;
        File file = new File(Path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

}
