package com.lxkj.jxls.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxkj.jxls.dao.ImportDao;
import com.lxkj.jxls.entity.EntityOneDb;
import com.lxkj.jxls.entity.ImportAttibute;
import com.lxkj.jxls.entity.MessageCode;
import com.lxkj.jxls.entity.PathEnum;
import com.lxkj.jxls.interfaces.ImportHandler;
import com.lxkj.jxls.util.CglibBean;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lxkj.jxls.util.CglibBean.createTBean;
import static com.lxkj.jxls.util.ConfigFile.getDatafromFile;
import static com.lxkj.jxls.util.ConfigFile.savaExcelData;
import static com.lxkj.jxls.util.Util.JsonToObject;
import static com.lxkj.jxls.util.Util.tableNameIsExist;

/**
 * @Author: ziv
 * @Date: 2018/4/28 12:08
 * @Description:
 */
public class ImportImp implements ImportHandler {

    static Logger logger = LoggerFactory.getLogger(ImportImp.class);


    @Override
    public String handler(String tableName, String excelPath) {
        String impor = tableNameIsExist(getDatafromFile(PathEnum.CONFIG_NAME.getPath()),tableName);
        if(impor != null){
            ImportAttibute importAttibute = JsonToObject(impor);
            List<EntityOneDb> oneDbs = importAttibute.getEntityOneDbs();
            CglibBean bean = null;
            try {
                bean = createTBean(oneDbs);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            // 获得bean的实体
            Object object = bean.getObject();
            //提取EXCEL文件信息
            List<Object> objects = extract(object,excelPath,importAttibute);
            new ImportDao().creatTableFromJson(importAttibute);
            //向数据库添加数据
            new ImportDao().addDataDao(importAttibute,objects);
            return MessageCode.getByCode(200).getCode();
        }else {
            logger.info("无此表格信息");
            return MessageCode.getByCode(808).getCode();
        }
    }


    /**
     * 提取Excel信息
     */
    public <T> List<T> extract(T t,String excelPath,ImportAttibute importAttibute){
        String pathName = t.getClass().getName();
        if(new CreateXml().createXml(importAttibute,pathName)){
            String[] typeNameString = pathName.split("\\.");
            return readData(t,typeNameString[typeNameString.length-1]+".xml",excelPath);
        }else {
            logger.info("返回的为null");
            return null;
        }
    }


    public <T> List<T> readData(T t,String xmlPath,String excelPath){
        List<T> genericities = new ArrayList<>();
        try {
            InputStream inputXML = new BufferedInputStream(new FileInputStream(new File(PathEnum.CONFIGURATION_PATH.getPath() + xmlPath)));
            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(new File(excelPath)));
            Map<String,Object> beans = new HashMap<String,Object>();
            beans.put("EntityList", genericities);
            XLSReadStatus readStatus = mainReader.read(inputXLS,beans);
            if(readStatus.isStatusOK()){
                logger.info("读取Excel成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genericities;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        List<EntityOneDb> entityOneDbs = new ArrayList<>();
        entityOneDbs.add(new EntityOneDb("2","城市","en_arrive","db_city"));
        entityOneDbs.add(new EntityOneDb("3","学校","en_start","db_school"));
        ImportAttibute data = new ImportAttibute("table_test1","铁路表格","铁路数据","1","1","3",entityOneDbs);
        savaExcelData(data,"excelConfig");
        String r = new ImportImp().handler("table_test1","D:\\workInformation\\2017.xlsx");
        System.out.println(r);
    }


}
