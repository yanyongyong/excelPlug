package primary;

import util.ExcelRowColformat;
import util.Genericity;
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

import static util.CreateXmlUtill.createXml;


/**
 * 类　名: primary.Import
 * 包　名: PACKAGE_NAME
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/16 11:12
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 描　述: EXCEL文件信息获取
 */
public class Import {

    static Logger logger = LoggerFactory.getLogger(Import.class);

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/17 14:57
     * @Description:
     * @param t 实体类型, startRow Excel标题的开头行, endRow Excel标题结束行, excelRowColformatList 实体类字段和EXCEL表中对应的列, path excel路径
     * @return java.util.List<T> 读取Excel数据
     */
    public static <T> List<T> readExcelData(T t,String startRow,String endRow,List<ExcelRowColformat> excelRowColformatList,String path){
        if(createXml(t,startRow,endRow,excelRowColformatList)){
            String pathName = t.getClass().getName();
            String[] typeNameString = pathName.split("\\.");
            return readData(t,typeNameString+".xml",path);
        }else {
            logger.info("返回的为null");
            return null;
        }
    }

    /**
     * 使用jxls解析导入的Excel
     * @param path 导入文件路径
     * @return List<RailwayEntity> 导入对象集合
     */
    public static  <T> List<T> readData(T t,String xmlPath,String path){
        List<T> genericities = new ArrayList<>();
        try {
            InputStream inputXML = new BufferedInputStream(Import.class.getClassLoader().getResourceAsStream("Entity.xml"));
            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(new File(path)));
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


   /* public <T> Genericity<T> entityGen(T t){
        System.out.println("toString:"+t.getClass());
        Genericity<T> genericity = new Genericity<>();
        genericity.setT(t);
        return genericity;
    }*/

}
