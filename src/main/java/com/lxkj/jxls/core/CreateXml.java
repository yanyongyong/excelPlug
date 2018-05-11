package com.lxkj.jxls.core;

import com.lxkj.jxls.entity.EntityOneDb;
import com.lxkj.jxls.entity.ImportAttibute;
import com.lxkj.jxls.entity.PathEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


/**
 * @Author: ziv
 * @Date: 2018/4/28 10:19
 * @Description: 生成xml
 */
public class CreateXml {

    static Logger logger = LoggerFactory.getLogger(CreateXml.class);


    /**
     * 创建XML模板
     * @param importAttibute
     * @param entityPath 实体类路径
     * @return
     */
    public Boolean createXml(ImportAttibute importAttibute,String entityPath){
//        ImportAttibute importAttibute = jsonToObject(onejsonData);
//        String pathName = t.getClass().getName();
        String[] typeNameString = entityPath.split("\\.");
//        System.out.println(typeNameString.length);
        String typeName = typeNameString[typeNameString.length-1];
        try{
            // 创建解析器工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            document.setXmlStandalone(true);
            Element workbook = document.createElement("workbook");
            // 向workbook根节点中添加子节点worksheet
            Element worksheet = document.createElement("worksheet");
            workbook.appendChild(worksheet);
            Element section = document.createElement("section");
            worksheet.appendChild(section);
            worksheet.setAttribute("idx", "0");
            section.setAttribute("startRow",importAttibute.getStartRow());
            section.setAttribute("endRow",importAttibute.getEndRow());
            Element loop = document.createElement("loop");
            loop.setAttribute("startRow","1");
            loop.setAttribute("endRow","1");
            loop.setAttribute("items","EntityList");
            loop.setAttribute("var",typeName);
            loop.setAttribute("varType",entityPath);
            worksheet.appendChild(loop);
            Element sectionInside = document.createElement("section");
            sectionInside.setAttribute("startRow","1");
            sectionInside.setAttribute("endRow","1");
            loop.appendChild(sectionInside);
            for (EntityOneDb eod: importAttibute.getEntityOneDbs() ){
                Element mapping = document.createElement("mapping");
                mapping.setAttribute("row",importAttibute.getRow());
                mapping.setAttribute("col",eod.getCol());
                mapping.setTextContent(typeName+"."+eod.getEntityName());
                sectionInside.appendChild(mapping);
            }
            Element loopbreakcondition = document.createElement("loopbreakcondition");
            loop.appendChild(loopbreakcondition);
            Element rowcheck = document.createElement("rowcheck");
            rowcheck.setAttribute("offset","0");
            loopbreakcondition.appendChild(rowcheck);
            Element cellcheck = document.createElement("cellcheck");
            cellcheck.setAttribute("offset","0");
            rowcheck.appendChild(cellcheck);
            document.appendChild(workbook);
            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建 Transformer对象
            Transformer tf = tff.newTransformer();
            // 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            // 创建xml文件并写入内容
            tf.transform(new DOMSource(document), new StreamResult(new File(PathEnum.CONFIGURATION_PATH.getPath()+ typeName+".xml")));
            logger.info("生成"+typeName+".xml成功");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("生成"+typeName+".xml文件失败");
            return false;
        }
    }

}
