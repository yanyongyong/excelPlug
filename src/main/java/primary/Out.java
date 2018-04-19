package primary;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import util.JxlsUtils;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

import static util.util.splicString;


/**
 * 类　名: Out
 * 包　名: primary
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/17 15:58
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 描　述: Excel数据导出
 */
public class Out {

    private static final String TEMPLATE_PAGE_PATH= "src\\main\\java\\template\\";

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/18 16:56
     * @Description:导出数据到Excel表格
     * @param t 导出数据LIst集合, sheetName sheet名字, removeField 实体类不需要的字段, outExcelPath 保存导出Excel路径
     * @return void
     */
    public static <T> void outDataToExcel(List<String> titleList,List<T> t,String sheetName,String removeField,String title,String outExcelPath) throws Exception {
        String className = getClassName(t);
        createExcelTemplate(titleList,TEMPLATE_PAGE_PATH+className+".xls","sheels", t,removeField,title);
        outData(t,className+".xls",outExcelPath);
    }


    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/17 16:58
     * @Description: 提取需要导出的字段
     * @param  e ,remove 不需要生成的字段(比如id,多个用,号隔开)
     * @return
     */
    public static <T> List<String> reflect(List<T> e,String remove) throws Exception{
        List<String> strings = splicString(remove,",");
        List<String> attributeNames = new ArrayList<>();
        List<String> newList = new ArrayList<>();
        Class cls = e.get(0).getClass();
        Field[] fields = cls.getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            f.setAccessible(true);
            attributeNames.add(f.getName());
        }
        for (String cd:attributeNames) {
            if(!strings.contains(cd)){
                newList.add(cd);
            }
        }
        return newList;
    }

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/18 15:44
     * @Description:导出数据到Excel表格
     * @param t, templateExcelPath 模板路径, outExcelPath 导出路径
     * @return void
     */
    public static  <T> void outData(List<T> t,String templateExcelPath,String outExcelPath) throws IOException{
        List<T> employees = t;
        String className = getClassName(t);
        OutputStream os = new FileOutputStream(outExcelPath);
        Map<String ,Object> model = new HashMap<String ,Object>();
        model.put(className, employees);
        model.put("nowdate", new Date());
        JxlsUtils.exportExcel(templateExcelPath, os, model);
        os.close();
    }

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/18 8:09
     * @Description:创建导出Excel数据模板
     * @param templatePath 存放Excel模板的路径, sheetName sheet名称, t, remove 不需要生成的字段（比如id，多个用,号隔开）
     * @return void
     */
    public static  <T> void createExcelTemplate(List<String> titleList,String templatePath,String sheetName,List<T> t,String remove,String title){
        String typeName = getClassName(t);
        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeight((short)300);
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow head = sheet.createRow((int) 0);
        HSSFRow row = sheet.createRow((int) 2);
        HSSFRow row2 = sheet.createRow((int) 3);
        //第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        style.setFont(font);
        HSSFPatriarch patr = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置，前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
        HSSFComment comment = patr.createComment(new HSSFClientAnchor(0,0,0,0, (short)4, 2 ,(short) 6, 5));
        HSSFComment comment2 = patr.createComment(new HSSFClientAnchor(0,0,0,0, (short)4, 4 ,(short) 6, 5));
        try {
            List<String> arrtName = reflect(t,remove);
            CellRangeAddress cra = new CellRangeAddress(0, 1, 0, arrtName.size()-1);
            //在sheet里增加合并单元格
            sheet.addMergedRegion(cra);
            // 设置标注内容
            comment.setString(new HSSFRichTextString("jx:area(lastCell=\"Z4\")"));
            comment2.setString(new HSSFRichTextString("jx:each(items=\""+typeName+"\" var=\""+typeName+"\" lastCell=\"Z4\")"));
            HSSFCell area = head.createCell(0);
            area.setCellStyle(style);
            HSSFCell iteams = row2.createCell(0);
//            area.setCellValue("时间:${utils:dateFmt(nowdate,\"yyyy-mm-dd\")}");
            area.setCellValue(title);
            area.setCellComment(comment);
            iteams.setCellComment(comment2);
            for (int i = 0;i<arrtName.size();i++){
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(titleList.get(i));
                HSSFCell cell2 = row2.createCell(i);
                cell2.setCellValue("${"+typeName+"."+arrtName.get(i)+"}");
            }
            FileOutputStream fout = new FileOutputStream(templatePath);
            wb.write(fout);
            fout.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/18 17:05
     * @Description: 获取类名称
     * @param
     * @return 类名称
     */
    private static <T> String getClassName(List<T> t) {
        String pathName = t.get(0).getClass().getName();
        String[] typeNameString = pathName.split("\\.");
        return typeNameString[typeNameString.length-1].toLowerCase();
    }



}
