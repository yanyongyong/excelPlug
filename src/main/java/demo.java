import util.ExcelRowColformat;
import org.junit.jupiter.api.Test;
import primary.Entity;

import java.util.ArrayList;
import java.util.List;

import static primary.Import.readExcelData;
import static primary.Out.outDataToExcel;

/**
 * All rights Reserved, Designed By LXKJ
 * 类　名: demo
 * 包　名: PACKAGE_NAME
 * 所　属: 路讯科技
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/17 15:18
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 修改人：无
 * 注　意：本内容仅限于路讯科技内部传阅，禁止外泄以及用于其他的商业目的
 * 描　述: Excel导入导出demo
 */
public class demo {

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/18 17:24
     * @Description:从Excel导入数据
     */
    @Test
    public void testImportExcelData(){
        Entity entity = new Entity();
        // Excel列数据和实体字段一一对应
        List<ExcelRowColformat> list = new ArrayList<>();
         //获取从第一行（除去标题的第一行）开始第二列的所有数据
        list.add(new ExcelRowColformat("2","1","name"));
        list.add(new ExcelRowColformat("3","1","sex"));
        //提取EXCEL文件信息
        List<Entity> entityList = readExcelData(entity,"1","4",list,"D:\\workInformation\\2017.xls");
        for (Entity e: entityList){
            System.out.println(e.getName());
        }
        System.out.println(entityList.size()+":...................");
    }


    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/18 17:24
     * @Description:导出数据到Excel
     */
    @Test
    public void testOutExcelData(){
        try {
            //第一个参数导出实体类的List数据（如List<Entity>），第二个参数设置Excel表sheet的名称
            // 第三个参数实体类不需要导出数据的字段（如 id）,第四个参数保存Excel的地址
            outDataToExcel(generateSampleEmployeeData(),"sheet","id","D:\\6.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 测试数据
     * @return
     */
    public static List<Entity> generateSampleEmployeeData(){
        List<Entity> employees = new ArrayList<Entity>();
        employees.add( new Entity("1", "沿革", "你好") );
        employees.add( new Entity("2", "沿革", "你好") );
        employees.add( new Entity("3", "沿革", "你好") );
        employees.add( new Entity("4", "沿革", "你好") );
        employees.add( new Entity("5", "沿革", "你好") );
        return employees;
    }

}
