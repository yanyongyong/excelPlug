package com.lxkj.jxls.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxkj.jxls.core.CreateXml;
import com.lxkj.jxls.entity.ImportAttibute;
import org.slf4j.LoggerFactory;

import java.io.*;

import static com.lxkj.jxls.entity.PathEnum.CONFIGURATION_PATH;
import static com.lxkj.jxls.util.Util.tableNameIsExist;

/**
 * @Author: ziv
 * @Date: 2018/5/2 10:12
 * @Description: 配置文件工具类
 */
public class ConfigFile {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(CreateXml.class);

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/26 11:59
     * @Description:
     * @return  true 表示保存成功 false 表示表名重复或者此配置已经有了
     */
    public static Boolean savaExcelData(ImportAttibute data,String excelConfigName) {
        Boolean info = false;
        File file = new File(CONFIGURATION_PATH.getPath() + excelConfigName + ".properties");
        String excelCofigData = JSON.toJSONString(data);
        if(!file.exists()){
            try {
                file.createNewFile();
                saveDataToFile(excelConfigName,excelCofigData);
                info = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            logger.info(excelCofigData);
            String isExist = tableNameIsExist(getDatafromFile(excelConfigName ),data.getTableName());
            if (isExist == null){
                saveDataToFile(excelConfigName ,excelCofigData);
                info = true;
            }else {
                logger.info("已经有此信息了或者表名重复。。。。。。");
            }
        }
        return info;
    }


    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/25 10:09
     * @Description: 读取文件
     */
    public static String getDatafromFile(String fileName) {
        String Path = CONFIGURATION_PATH.getPath() + fileName+ ".properties";
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        laststr = "[" + laststr.substring(1) + "]";
        return laststr;
    }


    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/25 10:09
     * @Description: 写入文件
     */
    public static void saveDataToFile(String fileName,String data){
        data = "," + data + "\r\n";
        BufferedWriter writer = null;
        File file = new File(CONFIGURATION_PATH.getPath() + fileName + ".properties");
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
