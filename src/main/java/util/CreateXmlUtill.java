package util;

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
import java.util.List;


/**
 * 类　名: CreateXmlUtill
 * 包　名: util
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/16 13:01
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 描　述: 生成XML文件工具类
 */
public class CreateXmlUtill {

    static Logger logger = LoggerFactory.getLogger(CreateXmlUtill.class);

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/17 8:34
     * @Description:
     * @param t 实体类型, startRow Excel标题的开头行, endRow Excel标题结束行, excelRowColformatList 实体类字段和EXCEL表中对应的列
     * @return java.lang.Boolean xml是否生成成功
     */
    public static <T> Boolean createXml(T t,String startRow,String endRow,List<ExcelRowColformat> excelRowColformatList){

        String pathName = t.getClass().getName();
        String[] typeNameString = pathName.split("\\.");
        System.out.println(typeNameString.length);
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
            section.setAttribute("startRow",startRow);
            section.setAttribute("endRow",endRow);
            Element loop = document.createElement("loop");
            loop.setAttribute("startRow","1");
            loop.setAttribute("endRow","1");
//            loop.setAttribute("items",typeName+"List");
            loop.setAttribute("items","EntityList");
            loop.setAttribute("var",typeName);
            loop.setAttribute("varType",pathName);
            worksheet.appendChild(loop);
            Element sectionInside = document.createElement("section");
            sectionInside.setAttribute("startRow","1");
            sectionInside.setAttribute("endRow","1");
            loop.appendChild(sectionInside);
            for (ExcelRowColformat ercf: excelRowColformatList ){
                Element mapping = document.createElement("mapping");
                mapping.setAttribute("row",ercf.getRow());
                mapping.setAttribute("col",ercf.getCol());
                mapping.setTextContent(typeName+"."+ercf.getEntityFiledName());
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
            tf.transform(new DOMSource(document), new StreamResult(new File("src/main/resources/"+typeName+".xml")));
            logger.info("生成"+typeName+".xml成功");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("生成"+typeName+".xml文件失败");
            return false;
        }
    }

}
