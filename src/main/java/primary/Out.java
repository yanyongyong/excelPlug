package primary;

import util.JxlsUtils;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;


/**
 * All rights Reserved, Designed By LXKJ
 * 类　名: Out
 * 包　名: primary
 * 所　属: 路讯科技
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/17 15:58
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 修改人：无
 * 注　意：本内容仅限于路讯科技内部传阅，禁止外泄以及用于其他的商业目的
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
    public static <T> void outDataToExcel(List<T> t,String sheetName,String removeField,String outExcelPath) throws Exception {
        String className = getClassName(t);
        createExcelTemplate(TEMPLATE_PAGE_PATH+className+".xls","sheels", t,"id");
        outData(t,className+".xls",outExcelPath);
    }


    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/17 16:58
     * @Description: 提取需要导出的字段
     * @param  e ,remove 不需要生成的字段(比如id)
     * @return
     */
    public static <T> List<String> reflect(List<T> e,String remove) throws Exception{
        List<String> attributeNames = new ArrayList<>();
        Class cls = e.get(0).getClass();
        Field[] fields = cls.getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            f.setAccessible(true);
            String name = f.getName();
            if (!name.equals(remove)){
                attributeNames.add(name);
            }
        }
        return attributeNames;
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
        OutputStream os = new FileOutputStream(outExcelPath);
        Map<String ,Object> model = new HashMap<String ,Object>();
        model.put("entity", employees);
//        model.put("nowdate", new Date());
        JxlsUtils.exportExcel(templateExcelPath, os, model);
        os.close();
    }

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/18 8:09
     * @Description:创建导出Excel数据模板
     * @param templatePath 存放Excel模板的路径, sheetName sheet名称, t, remove 不需要生成的字段（比如id）
     * @return void
     */
    public static  <T> void createExcelTemplate(String templatePath,String sheetName,List<T> t,String remove){
        String typeName = getClassName(t);
        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        HSSFRow row2 = sheet.createRow((int) 1);
        //第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        HSSFPatriarch patr = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置，前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
        HSSFComment comment = patr.createComment(new HSSFClientAnchor(0,0,0,0, (short)4, 2 ,(short) 6, 5));
        HSSFComment comment2 = patr.createComment(new HSSFClientAnchor(0,0,0,0, (short)4, 4 ,(short) 6, 5));
        try {
            List<String> arrtName = reflect(t,remove);
            arrtName.size();
            // 设置注释内容
            comment.setString(new HSSFRichTextString("jx:area(lastCell=\"Z4\")"));
            comment2.setString(new HSSFRichTextString("jx:each(items=\""+typeName+"\" var=\""+typeName+"\" lastCell=\"Z2\")"));
            HSSFCell area = row.createCell(0);
            HSSFCell iteams = row2.createCell(0);
            area.setCellComment(comment);
            iteams.setCellComment(comment2);
            for (int i = 0;i<arrtName.size();i++){
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(arrtName.get(i));
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
